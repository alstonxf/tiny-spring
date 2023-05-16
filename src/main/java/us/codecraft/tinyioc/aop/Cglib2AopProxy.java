package us.codecraft.tinyioc.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用CGLIB实现AOP代理的类
 * 该类实现了AOP代理的CGLIB2实现。具体来说，它实现了getProxy方法来返回代理对象，其中代理对象通过CGLIB的Enhancer实现。Cglib2AopProxy还有两个嵌套类：DynamicAdvisedInterceptor和CglibMethodInvocation。
 *
 * DynamicAdvisedInterceptor实现了CGLIB2的MethodInterceptor接口。它拦截目标方法的调用，然后通过代理的方法调用aopalliance.intercept.MethodInterceptor的invoke方法实现增强。CglibMethodInvocation继承了ReflectiveMethodInvocation，重写了proceed方法，调用目标方法。通过使用CGLIB2的MethodProxy实现目标方法的调用。
 */
public class Cglib2AopProxy extends AbstractAopProxy {

	public Cglib2AopProxy(AdvisedSupport advised) {
		super(advised);
	}

	/**
	 * 获取代理对象
	 */
	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
		enhancer.setInterfaces(advised.getTargetSource().getInterfaces());
		enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
		Object enhanced = enhancer.create();
		return enhanced;
	}

	/**
	 * CGLIB的方法拦截器
	 */
	private static class DynamicAdvisedInterceptor implements MethodInterceptor {

		private AdvisedSupport advised;

		private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

		private DynamicAdvisedInterceptor(AdvisedSupport advised) {
			this.advised = advised;
			this.delegateMethodInterceptor = advised.getMethodInterceptor();
		}

		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			// 判断方法是否需要被拦截
			if (advised.getMethodMatcher() == null
					|| advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass())) {
				// 如果需要被拦截，则执行代理的方法拦截器
				return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy));
			}
			// 如果不需要被拦截，则直接执行原始方法
			return new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy).proceed();
		}
	}

	/**
	 * CGLIB的方法调用
	 */
	private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

		private final MethodProxy methodProxy;

		public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
			super(target, method, args);
			this.methodProxy = methodProxy;
		}

		@Override
		public Object proceed() throws Throwable {
			return this.methodProxy.invoke(this.target, this.arguments);
		}
	}

}
