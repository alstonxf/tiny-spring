package us.codecraft.tinyioc.beans;

/**
 * 用于bean的属性注入
 *
 * 表示一个属性及其对应的值
 *
 * @author yihua.huang@dianping.com
 */
public class PropertyValue {

    private final String name; // 属性名

    private final Object value; // 属性值

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
