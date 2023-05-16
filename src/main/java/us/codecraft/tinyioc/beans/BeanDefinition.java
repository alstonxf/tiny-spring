package us.codecraft.tinyioc.beans;

/**
 * bean的内容及元数据，保存在BeanFactory中，包装bean的实体
 *
 * bean的定义类，保存BeanFactory中的bean内容及元数据
 * bean实体（bean）即为定义类中的对象bean
 *
 * @param bean 实体bean对象
 * @param beanClass 实体bean的Class对象
 * @param beanClassName 实体bean的类名
 * @param propertyValues 实体bean的属性列表
 *
 * @see BeanFactory
 * @see PropertyValues
 *
 * @author yihua.huang@dianping.com
 */
public class BeanDefinition {

	private Object bean; // 实体bean对象

	private Class beanClass; // 实体bean的Class对象

	private String beanClassName; // 实体bean的类名

	private PropertyValues propertyValues = new PropertyValues(); // 实体bean的属性列表

	public BeanDefinition() { // 默认构造函数
	}

	public void setBean(Object bean) { // 设置实体bean对象
		this.bean = bean;
	}

	public Class getBeanClass() { // 获取实体bean的Class对象
		return beanClass;
	}

	public void setBeanClass(Class beanClass) { // 设置实体bean的Class对象
		this.beanClass = beanClass;
	}

	public String getBeanClassName() { // 获取实体bean的类名
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) { // 设置实体bean的类名
		this.beanClassName = beanClassName;
		try { // 通过反射获取实体bean的Class对象
			this.beanClass = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Object getBean() { // 获取实体bean对象
		return bean;
	}

	public PropertyValues getPropertyValues() { // 获取实体bean的属性列表
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) { // 设置实体bean的属性列表
		this.propertyValues = propertyValues;
	}
}
