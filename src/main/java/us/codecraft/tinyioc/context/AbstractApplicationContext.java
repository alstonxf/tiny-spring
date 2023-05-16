package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.BeanPostProcessor;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * 抽象ApplicationContext类，实现了ApplicationContext接口，作为ApplicationContext接口的中间实现类
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

	protected AbstractBeanFactory beanFactory;

	public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	/**
	 * 刷新应用上下文，即根据BeanDefinition注册Bean
	 * @throws Exception
	 */
	public void refresh() throws Exception {
		loadBeanDefinitions(beanFactory); // 加载BeanDefinition
		registerBeanPostProcessors(beanFactory); // 注册BeanPostProcessor
		onRefresh(); // 实例化所有单例Bean
	}

	/**
	 * 加载BeanDefinition到BeanFactory中
	 * @param beanFactory
	 * @throws Exception
	 */
	protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

	/**
	 * 注册BeanPostProcessor到BeanFactory中
	 * @param beanFactory
	 * @throws Exception
	 */
	protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
		List<BeanPostProcessor> beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			beanFactory.addBeanPostProcessor(beanPostProcessor);
		}
	}

	/**
	 * 实例化所有单例Bean
	 * @throws Exception
	 */
	protected void onRefresh() throws Exception{
		beanFactory.preInstantiateSingletons();
	}

	/**
	 * 获取Bean实例
	 * @param name Bean名称
	 * @return Bean实例
	 * @throws Exception
	 */
	@Override
	public Object getBean(String name) throws Exception {
		return beanFactory.getBean(name);
	}
}
