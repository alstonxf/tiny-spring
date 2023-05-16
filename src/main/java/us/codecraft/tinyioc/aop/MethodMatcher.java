package us.codecraft.tinyioc.aop;

import java.lang.reflect.Method; // 引入Java反射机制中的Method类

/**
 * 定义了一个用于匹配方法是否符合规则的接口
 *
 * author yihua.huang@dianping.com
 */
public interface MethodMatcher {

    // 判断方法是否符合规则
    boolean matches(Method method, Class targetClass);
}
