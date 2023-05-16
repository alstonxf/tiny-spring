package us.codecraft.tinyioc.beans;

import us.codecraft.tinyioc.beans.io.ResourceLoader; // 引入 ResourceLoader 类，用于加载资源

import java.util.HashMap; // 引入 HashMap 类，用于实现 Map 接口
import java.util.Map; // 引入 Map 接口，用于存储键值对

/**
 * 从配置中读取 BeanDefinition 的抽象类
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String,BeanDefinition> registry; // 存储 BeanDefinition 的 Map
    private ResourceLoader resourceLoader; // 加载资源的 ResourceLoader 对象

    /**
     * 构造函数，用于初始化 registry 和 resourceLoader
     *
     * @param resourceLoader 加载资源的 ResourceLoader 对象
     */
    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取 registry
     *
     * @return 存储 BeanDefinition 的 Map
     */
    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    /**
     * 获取 resourceLoader
     *
     * @return 加载资源的 ResourceLoader 对象
     */
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
