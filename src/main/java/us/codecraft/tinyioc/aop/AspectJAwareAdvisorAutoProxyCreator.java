package us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import us.codecraft.tinyioc.beans.BeanPostProcessor;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.beans.factory.BeanFactory;

import java.util.List;

/**
 * 实现了 BeanPostProcessor 接口，用于创建代理对象并将代理对象注入到容器中
 * 这段代码实现了BeanPostProcessor接口和BeanFactoryAware接口，可以在Bean初始化前和初始化后对Bean进行一些处理。
 * 具体地，当Bean不是AspectJExpressionPointcutAdvisor或MethodInterceptor类型时，
 * 它会从BeanFactory中获取所有的AspectJExpressionPointcutAdvisor类型的Bean，并尝试为该Bean创建代理对象。
 * 对于每个AspectJExpressionPointcutAdvisor，它会检查切点是否匹配该Bean的类。如果匹配，则使用ProxyFactory为该Bean创建代理对象。
 * 然后，将代理对象的目标源设置为原始Bean，并将其方法拦截器和方法匹配器设置为该Advisor的Advice和Pointcut。最后，它返回代理对象。
 * 在整个过程中，它使用了BeanFactory和AbstractBeanFactory等多个Spring框架中的类。
 * 它通过这些类来获取BeanFactory的实例和BeanFactory中所有的Advisor Bean，以及为目标对象创建代理对象。
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	// AbstractBeanFactory 是 BeanFactory 接口的实现类，用于管理容器中的 Bean 对象
	private AbstractBeanFactory beanFactory;

	/**
	 * 在 Bean 初始化之前不做任何处理，直接返回原始的 Bean 对象
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
		return bean;
	}

	/**
	 * 在 Bean 初始化之后，检查是否需要创建代理对象，并将代理对象注入到容器中
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		// 如果当前 Bean 是 AspectJExpressionPointcutAdvisor 或 MethodInterceptor 类型，则不做处理，直接返回原始的 Bean 对象
		//因为这两种类型的 Bean 本身就是用来实现 AOP 的。如果不加判断直接创建代理对象，会导致原始 Bean 对象被多次代理，产生副作用。因此，需要在这里进行判断，避免对这两种类型的 Bean 进行代理处理。
		if (bean instanceof AspectJExpressionPointcutAdvisor || bean instanceof MethodInterceptor) {
			return bean;
		}
		// 获取容器中所有的 AspectJExpressionPointcutAdvisor 对象
		List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
		for (AspectJExpressionPointcutAdvisor advisor : advisors) {
			// 判断当前 Bean 是否需要创建代理对象
			if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
				// 创建代理对象
				ProxyFactory advisedSupport = new ProxyFactory();
				advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
				advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

				TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
				advisedSupport.setTargetSource(targetSource);

				// 将代理对象注入到容器中，替换原始的 Bean 对象
				return advisedSupport.getProxy();
			}
		}
		// 如果当前 Bean 不需要创建代理对象，则直接返回原始的 Bean 对象
		return bean;
	}

	/**
	 * 实现了 BeanFactoryAware 接口，用于设置 beanFactory 实例
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws Exception {
		this.beanFactory = (AbstractBeanFactory) beanFactory;
	}
}
