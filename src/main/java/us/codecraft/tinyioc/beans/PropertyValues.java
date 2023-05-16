package us.codecraft.tinyioc.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装一个对象所有的PropertyValue。
 * 为什么封装而不是直接用List? 因为可以封装一些操作。
 * 例如在设置属性时，可以进行一些额外操作，如对属性名称进行判断等。
 * 这些操作可以通过自定义PropertyValues来实现。
 *
 * 每个PropertyValue对应于一个具体的属性，存储了该属性的名称和值。
 *
 * @author yihua
 */
public class PropertyValues {

	// 用于存储对象所有的PropertyValue
	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

	public PropertyValues() {
	}

	/**
	 * 添加一个PropertyValue到列表中
	 * @param pv 要添加的PropertyValue对象
	 */
	public void addPropertyValue(PropertyValue pv) {
		//TODO:这里可以对于重复propertyName进行判断，直接用list没法做到
		this.propertyValueList.add(pv);
	}

	/**
	 * 获取所有的PropertyValue对象
	 * @return PropertyValue列表
	 */
	public List<PropertyValue> getPropertyValues() {
		return this.propertyValueList;
	}

}
