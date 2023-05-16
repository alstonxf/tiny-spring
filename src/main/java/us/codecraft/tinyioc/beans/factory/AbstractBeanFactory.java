package us.codecraft.tinyioc.beans.factory;

import us.codecraft.tinyioc.beans.BeanDefinition;
import us.codecraft.tinyioc.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory 的抽象实现类，提供了一些通用的实现
 */
public abstract class AbstractBeanFactory implements BeanFactory {

	// 存放 BeanDefinition 的 Map
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	// 存放 BeanDefinition 名称的 List
	private final List<String> beanDefinitionNames = new ArrayList<String>();

	// 存放 BeanPostProcessor 的 List
	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

	/**
	 * 根据 bean 名称获取 bean 实例
	 *
	 * @param name bean 名称
	 * @return bean 实例
	 * @throws Exception
	 */
	@Override
	public Object getBean(String name) throws Exception {
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if (beanDefinition == null) {
			throw new IllegalArgumentException("No bean named " + name + " is defined");
		}
		Object bean = beanDefinition.getBean();
		if (bean == null) {
			// 如果还未创建，则创建并初始化 bean，并将其设置到 BeanDefinition 中
			bean = doCreateBean(beanDefinition);
			bean = initializeBean(bean, name);
			beanDefinition.setBean(bean);
		}
		return bean;
	}

	/**
	 * 初始化 bean
	 *
	 * @param bean bean 实例
	 * @param name bean 名称
	 * @return 初始化后的 bean
	 * @throws Exception
	 */
	protected Object initializeBean(Object bean, String name) throws Exception {
		// 调用 BeanPostProcessor 的 postProcessBeforeInitialization 方法
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}

		// TODO: 调用 bean 的初始化方法

		// 调用 BeanPostProcessor 的 postProcessAfterInitialization 方法
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
		}
		return bean;
	}

	/**
	 * 创建 bean 实例
	 *
	 * @param beanDefinition BeanDefinition 实例
	 * @return 创建的 bean 实例
	 * @throws Exception
	 */
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}

	/**
	 * 注册 BeanDefinition
	 *
	 * @param name           bean 名称
	 * @param beanDefinition BeanDefinition 实例
	 * @throws Exception
	 */
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
		beanDefinitionMap.put(name, beanDefinition);
		beanDefinitionNames.add(name);
	}

	/**
	 * 预先创建所有单例 bean 的实例
	 *
	 * @throws Exception
	 */
 	public void preInstantiateSingletons() throws Exception {
		for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
			// 遍历beanDefinitionNames集合中的每个元素
			String beanName = (String) it.next();
			// 根据beanName获取对应的Bean实例
			getBean(beanName);
		}
	}

	/**
	 根据BeanDefinition创建一个新的Bean实例
	 @param beanDefinition Bean的定义
	 @return 创建的Bean实例
	 @throws Exception 如果Bean创建过程中出现错误
	 */
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	/**
	 将属性值应用于Bean实例
	 @param bean Bean实例
	 @param beanDefinition Bean的定义
	 @throws Exception 如果属性值应用过程中出现错误
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
	}

	/**
	 添加一个BeanPostProcessor，它会在Bean实例化和依赖注入后立即调用
	 @param beanPostProcessor 要添加的BeanPostProcessor
	 @throws Exception 如果添加过程中出现错误
	 */
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
		this.beanPostProcessors.add(beanPostProcessor);
	}

	/**
	 根据类型获取所有符合条件的Bean
	 @param type 要查找的Bean类型
	 @return 符合条件的Bean列表
	 @throws Exception 如果查找过程中出现错误
	 */
	public List getBeansForType(Class type) throws Exception {
		List beans = new ArrayList<Object>();
		for (String beanDefinitionName : beanDefinitionNames) {
			if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
	}

}
