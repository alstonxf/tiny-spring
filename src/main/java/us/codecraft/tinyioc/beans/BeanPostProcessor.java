package us.codecraft.tinyioc.beans;

/**
 * 这段代码定义了一个BeanPostProcessor接口，其中包含两个方法：
 * postProcessBeforeInitialization和postProcessAfterInitialization。这两个方法分别在Bean初始化前和初始化后被调用，用于在Bean实例化和初始化的过程中进行一些处理。
 * 这个BeanPostProcessor接口在Spring框架中被广泛使用，它允许开发人员在Bean初始化的过程中对Bean进行一些额外的操作，比如：添加一些额外的属性，修改Bean的属性等等。
 */
public interface BeanPostProcessor {

	//在postProcessBeforeInitialization方法中，它接收两个参数：bean和beanName，
	// bean表示正在初始化的Bean对象，beanName表示正在初始化的Bean对象的名称。它返回一个Object对象，这个返回值可以是原始的bean对象，也可以是经过修改后的bean对象。
	Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

	// 在postProcessAfterInitialization方法中，它同样接收两个参数：bean和beanName。
	// 它返回一个Object对象，这个返回值可以是原始的bean对象，也可以是经过修改后的bean对象。
	Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;

}