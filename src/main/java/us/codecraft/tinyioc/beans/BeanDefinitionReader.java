package us.codecraft.tinyioc.beans;

/**
 * 从配置中读取BeanDefinition
 *
 * 定义了一个接口，用于从配置中读取BeanDefinition
 *
 * @author yihua.huang@dianping.com
 */
public interface BeanDefinitionReader {

    /**
     * 从指定位置加载BeanDefinition
     *
     * @param location 配置文件的路径
     * @throws Exception
     */
    void loadBeanDefinitions(String location) throws Exception;
}
