package us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor; // 引入AOP联盟的MethodInterceptor接口

import java.lang.reflect.InvocationHandler; // 引入Java反射机制中的InvocationHandler接口
import java.lang.reflect.Method; // 引入Java反射机制中的Method类
import java.lang.reflect.Proxy; // 引入Java反射机制中的Proxy类

/**
 * 基于jdk的动态代理
 * 实现了InvocationHandler接口，同时继承了AbstractAopProxy抽象类
 * 在getProxy()方法中通过反射生成代理对象
 * 在invoke()方法中实现了具体的方法拦截逻辑
 *
 * @author yihua.huang@dianping.com
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

	// 构造方法，传入需要代理的对象的相关信息
	public JdkDynamicAopProxy(AdvisedSupport advised) {
		super(advised);
	}

	// 获取代理对象
	@Override
	public Object getProxy() {
		// 使用Proxy.newProxyInstance方法创建代理对象
		// 第一个参数是代理对象的类加载器
		// 第二个参数是代理对象需要实现的接口
		// 第三个参数是InvocationHandler对象，用于拦截代理对象的方法
		return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getInterfaces(), this);
	}

	// 拦截代理对象的方法并进行处理
	@Override
	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		// 获取方法拦截器
		MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
		// 如果存在方法匹配器，并且当前方法符合匹配规则
		if (advised.getMethodMatcher() != null
				&& advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
			// 利用方法拦截器进行方法的拦截和处理
			return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(),
					method, args));
		} else {
			// 如果不存在方法匹配器或者当前方法不符合匹配规则，则直接调用原始对象的方法
			return method.invoke(advised.getTargetSource().getTarget(), args);
		}
	}

}
