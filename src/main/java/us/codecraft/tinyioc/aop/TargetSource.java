package us.codecraft.tinyioc.aop;

/**
 *  TargetSource 类，用来封装被代理的对象。
 * 被代理的对象的相关信息，包括类、接口和实例
 *
 * 注意：这个类没有提供 setter 方法，这是故意的，因为这个类的实例是不可变的。
 * 这种设计方式可以提高线程安全性，同时也使代码更加简单易懂。
 */
public class TargetSource {

	// 被代理对象的类型
	private Class<?> targetClass;

	// 被代理对象实现的接口类型
	private Class<?>[] interfaces;

	// 被代理对象实例
	private Object target;

	/**
	 * 构造函数，用于创建一个新的 TargetSource 对象
	 *
	 * @param target 被代理对象实例
	 * @param targetClass 被代理对象的类型
	 * @param interfaces 被代理对象实现的接口类型
	 */
	public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
		this.target = target;
		this.targetClass = targetClass;
		this.interfaces = interfaces;
	}

	/**
	 * 获取被代理对象的类型
	 *
	 * @return 被代理对象的类型
	 */
	public Class<?> getTargetClass() {
		return targetClass;
	}

	/**
	 * 获取被代理对象实例
	 *
	 * @return 被代理对象实例
	 */
	public Object getTarget() {
		return target;
	}

	/**
	 * 获取被代理对象实现的接口类型
	 *
	 * @return 被代理对象实现的接口类型
	 */
	public Class<?>[] getInterfaces() {
		return interfaces;
	}
}
