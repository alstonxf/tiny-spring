package us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理相关的元数据
 *
 * AdvisedSupport 类为代理相关的元数据提供了基本的存储和访问方法。
 * 它包含三个成员变量：targetSource 代表目标对象的元数据，methodInterceptor 代表方法拦截器，methodMatcher 代表方法匹配器。
 *
 * 在 Spring 框架中，AdvisedSupport 类被用作 AOP 代理的配置类，用于保存代理对象的相关信息。
 * 代理对象可以通过 setTargetSource() 方法设置目标对象，通过 setMethodInterceptor() 方法设置方法拦截器，通过 setMethodMatcher() 方法设置方法匹配器。
 * 这些信息将被 AOP 框架用于生成代理对象。
 */
public class AdvisedSupport {

    /**
     * 目标对象的元数据
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;

    /**
     * 获取目标对象的元数据
     * @return 目标对象的元数据
     */
    public TargetSource getTargetSource() {
        return targetSource;
    }

    /**
     * 设置目标对象的元数据
     * @param targetSource 目标对象的元数据
     */
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    /**
     * 获取方法拦截器
     * @return 方法拦截器
     */
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    /**
     * 设置方法拦截器
     * @param methodInterceptor 方法拦截器
     */
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    /**
     * 获取方法匹配器
     * @return 方法匹配器
     */
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    /**
     * 设置方法匹配器
     * @param methodMatcher 方法匹配器
     */
    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
