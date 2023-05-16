package us.codecraft.tinyioc.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import us.codecraft.tinyioc.beans.factory.BeanFactory;

import java.lang.reflect.Method;

/**
 * 切面的环绕通知（Around Advice）
 * AspectJAroundAdvice 类实现了 Advice 和 MethodInterceptor 接口，用于处理切面的环绕通知。
 * 在 invoke 方法中，它使用反射调用切面的通知方法，并传入方法调用对象。该类还提供了访问和设置 Bean 工厂、切面通知方法和切面实例名称的方法，以便在 AOP 框架中使用。
 */

public class AspectJAroundAdvice implements Advice, MethodInterceptor {

    /**
     * Advice 是一个接口文件，命名空间为org.aopalliance.aop，其中定义了一个空接口Advice。这个接口应该是AOP联盟提供的一个通用接口，用于表示切面的通用概念，如拦截器、增强、建议等。由于该接口没有任何方法，因此它只起到标记的作用，用于将某个对象标识为Advice对象。
     * public interface Advice {
     * }
     *
     * // 方法拦截器接口，继承自 Interceptor 接口
     * public interface MethodInterceptor extends Interceptor {
     * // 调用该方法时会传入 MethodInvocation 对象，返回 Object 类型
     * Object invoke(MethodInvocation var1) throws Throwable;
     * }
     */

    // Bean工厂，用于获取切面对象
    private BeanFactory beanFactory;

    // 切面通知方法
    private Method aspectJAdviceMethod;

    // 切面实例的名称
    private String aspectInstanceName;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 通过反射调用切面的通知方法，并传入方法调用对象
        // 首先从容器中获取切面实例对象
        Object aspectInstance = beanFactory.getBean(aspectInstanceName);
        // 调用切面的通知方法，使用传入的 MethodInvocation 对象作为参数
        // 注意需要使用 aspectJAdviceMethod.invoke() 方法来调用，其中第一个参数是切面实例对象
        return aspectJAdviceMethod.invoke(aspectInstance, invocation);
    }


    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Method getAspectJAdviceMethod() {
        return aspectJAdviceMethod;
    }

    public void setAspectJAdviceMethod(Method aspectJAdviceMethod) {
        this.aspectJAdviceMethod = aspectJAdviceMethod;
    }

    public String getAspectInstanceName() {
        return aspectInstanceName;
    }

    public void setAspectInstanceName(String aspectInstanceName) {
        this.aspectInstanceName = aspectInstanceName;
    }
}
