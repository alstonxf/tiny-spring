package us.codecraft.tinyioc.aop;

import org.aopalliance.aop.Advice;

/**
 * AspectJExpressionPointcutAdvisor 类表示一个 AspectJ 表达式切点和通知的组合，
 * 它实现了 PointcutAdvisor 接口。
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 定义一个 AspectJExpressionPointcut 对象，用于表示切点
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    // 定义一个 Advice 对象，用于表示通知
    private Advice advice;

    /**
     * 设置通知对象
     *
     * @param advice 要设置的通知对象
     */
    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    /**
     * 设置切点表达式
     *
     * @param expression 要设置的切点表达式
     */
    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    /**
     * 获取通知对象
     *
     * @return 当前实例的 advice 属性
     */
    @Override
    public Advice getAdvice() {
        return advice;
    }

    /**
     * 获取切点对象
     *
     * @return 当前实例的 pointcut 属性
     */
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
