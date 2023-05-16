package us.codecraft.tinyioc.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 这是一个Java代码实现的AspectJ表达式切入点。在面向切面编程（AOP）中，切入点是一个规范，确定在代码中应该应用方面的位置。AspectJ表达式切入点是一种切入点，它使用AspectJ表达式来指定应用方面的连接点。
 *
 * AspectJExpressionPointcut类实现了Pointcut、ClassFilter和MethodMatcher接口。它使用AspectJ weaver库来解析和评估AspectJ表达式。
 *
 * 该类有一个构造函数，它以PointcutPrimitive对象的集合作为参数。PointcutPrimitive是AspectJ支持的不同连接点类型的枚举。DEFAULT_SUPPORTED_PRIMITIVES集合包含了在AspectJ表达式中常用的一组连接点原语。
 *
 * setExpression方法用于设置要评估的AspectJ表达式。内部调用checkReadyToMatch方法确保pointcutExpression字段已初始化并准备好进行评估。
 *
 * getClassFilter方法返回当前对象，该对象实现ClassFilter接口，而getMethodMatcher方法返回当前对象，该对象实现MethodMatcher接口。
 *
 * matches方法使用Class参数检查指定的目标类是否与切入点表达式匹配。使用Method和Class参数的matches方法检查指定目标类中的指定方法是否与切入点表达式匹配。
 *
 * 在后一种情况下，该方法首先调用PointcutExpression对象的matchesMethodExecution方法以获取ShadowMatch对象。ShadowMatch对象表示将指定的方法执行与切入点表达式进行匹配的结果。然后，该方法检查ShadowMatch对象是否始终匹配或从不匹配，并返回true或false。在其他情况下，该方法返回false。
 */

public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
	//定义成员变量
	private PointcutParser pointcutParser;
	private String expression;
	private PointcutExpression pointcutExpression;
	//定义默认支持的切点原语
	private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

	//静态初始化块，初始化默认支持的切点原语
	static {
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
	}

	//构造函数
	public AspectJExpressionPointcut() {
		this(DEFAULT_SUPPORTED_PRIMITIVES);
	}

	//构造函数，传入需要支持的切点原语
	public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
		pointcutParser = PointcutParser
				.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
	}

	//检查是否可以匹配
	protected void checkReadyToMatch() {
		if (pointcutExpression == null) {
			pointcutExpression = buildPointcutExpression();
		}
	}

	//构建切点表达式
	private PointcutExpression buildPointcutExpression() {
		return pointcutParser.parsePointcutExpression(expression);
	}

	//设置表达式
	public void setExpression(String expression) {
		this.expression = expression;
	}

	//实现Pointcut接口的getClassFilter方法，返回自身作为ClassFilter
	@Override
	public ClassFilter getClassFilter() {
		return this;
	}

	// 实现Pointcut接口的getMethodMatcher方法
	@Override
	public MethodMatcher getMethodMatcher() {
		return this;
	}

	//实现ClassFilter接口的matches方法，匹配类
	@Override
	public boolean matches(Class targetClass) {
		checkReadyToMatch();
		return pointcutExpression.couldMatchJoinPointsInType(targetClass);
	}

	//实现MethodMatcher接口的matches方法，匹配方法
	@Override
	public boolean matches(Method method, Class targetClass) {
		checkReadyToMatch();
		ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
		if (shadowMatch.alwaysMatches()) {
			return true;
		} else if (shadowMatch.neverMatches()) {
			return false;
		}
		// TODO:其他情况不判断了！见org.springframework.aop.aspectj.RuntimeTestWalker
		return false;
	}



}