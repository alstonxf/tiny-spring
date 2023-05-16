package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.factory.BeanFactory;

/**
 * 应用上下文，继承BeanFactory接口，拥有BeanFactory所有功能
 * 可以让我们从Spring的BeanFactory转向自己实现的BeanFactory
 * ApplicationContext的实现需要除了BeanFactory之外，还需要具备资源加载的功能（ResourceLoader），
 * 事件发布、监听的功能（ApplicationEventPublisher），国际化消息解析的功能（MessageSource）
 * 在Web应用中还需要Servlet相关的功能等。
 *
 * 可以通过实现ApplicationContext来自定义自己的应用上下文，以满足特定的需求。
 *
 * 建议：如果只是作为一个轻量级的Bean容器使用，直接继承DefaultBeanFactory即可。
 * 如果需要更丰富的功能，比如国际化，事件监听等，再考虑继承实现ApplicationContext接口。
 *
 * 参考：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-introduction

 *
 * @author yihua.huang@dianping.com
 */
public interface ApplicationContext extends BeanFactory {
}
