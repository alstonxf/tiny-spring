package us.codecraft.tinyioc.beans.factory;

import us.codecraft.tinyioc.BeanReference;
import us.codecraft.tinyioc.aop.BeanFactoryAware;
import us.codecraft.tinyioc.beans.BeanDefinition;
import us.codecraft.tinyioc.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**

 可自动装配内容的BeanFactory
 在实现 AbstractBeanFactory 的基础上，实现了 BeanFactoryAware 接口，可以自动注入BeanFactory，以便于后续的操作。
 这是一个可自动装配内容的BeanFactory，实现了AbstractBeanFactory类。其中，applyPropertyValues方法将BeanDefinition中的属性值应用于Bean对象上。具体来说：

 如果Bean实现了BeanFactoryAware接口，则将BeanFactory设置到Bean中。
 对于每个属性，如果属性值是BeanReference，则获取BeanReference引用的Bean对象，并将其设置到该属性上。
 如果Bean中存在与属性名相同的setter方法，则调用该方法将属性值设置到Bean上。
 如果Bean中不存在与属性名相同的setter方法，则直接设置属性值到Bean的属性上。
 需要注意的是，该实现是基于JavaBean的属性注入方式进行的。
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
	/**
	 * 通过反射，将BeanDefinition中的属性值，赋给bean实例
	 *
	 * @param bean 实例
	 * @param mbd BeanDefinition对象
	 * @throws Exception
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
		// 如果bean实例实现了BeanFactoryAware接口，则注入beanFactory
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}

		// 遍历BeanDefinition的属性值列表，进行注入
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			Object value = propertyValue.getValue();
			// 如果属性值为BeanReference，获取其引用的实例对象
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getName());
			}

			try {
				// 通过set方法进行注入
				Method declaredMethod = bean.getClass().getDeclaredMethod(
						"set" + propertyValue.getName().substring(0, 1).toUpperCase()
								+ propertyValue.getName().substring(1), value.getClass());
				declaredMethod.setAccessible(true);
				declaredMethod.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				// 如果不存在对应的set方法，则通过反射，直接注入属性值
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}
		}
	}

}