package us.codecraft.tinyioc.beans.factory;

/**
 * BeanFactory 接口，定义了获取 bean 实例的方法
 *
 * author yihua.huang@dianping.com
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
