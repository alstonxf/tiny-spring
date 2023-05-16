package us.codecraft.tinyioc.aop;

/**
 * 定义了一个切点接口，用于定义拦截规则的过滤器和方法匹配器
 *
 * author yihua.huang@dianping.com
 */
public interface Pointcut {

    // 获取用于筛选类的过滤器
    ClassFilter getClassFilter();

    // 获取用于匹配方法的方法匹配器
    MethodMatcher getMethodMatcher();

}
