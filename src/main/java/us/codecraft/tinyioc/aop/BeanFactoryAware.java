package us.codecraft.tinyioc.aop;

import us.codecraft.tinyioc.beans.factory.BeanFactory;

/**
 *这段代码定义了一个BeanFactoryAware接口，它包含了一个setBeanFactory方法。这个方法接收一个BeanFactory类型的参数，用于将BeanFactory对象注入到实现了该接口的类中。
 *
 * 这个接口在AOP编程中经常被使用，因为在AOP编程中，需要使用到BeanFactory对象来获取代理对象。因此，在创建代理对象时，需要将BeanFactory对象注入到代理对象中。
 * 实现了BeanFactoryAware接口的类就可以通过实现setBeanFactory方法，将BeanFactory对象注入到自己的属性中。
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
