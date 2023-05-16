package us.codecraft.tinyioc.aop;

/**
 *该代码定义了一个接口ClassFilter，用于匹配目标类是否符合切点的类过滤规则。
 *
 * 方法matches(Class targetClass)用于判断传入的Class对象是否符合类过滤规则，如果符合则返回true，否则返回false。
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
