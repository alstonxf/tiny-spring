package us.codecraft.tinyioc;

/**
 * 用于保存一个 Bean 的引用，包括 Bean 的名称和 Bean 实例。
 * BeanReference 可以用于解决 Bean 之间的循环依赖问题。
 *
 * @author yihua.huang
 */
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
