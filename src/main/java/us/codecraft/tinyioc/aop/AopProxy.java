package us.codecraft.tinyioc.aop;

/**
 * 该接口定义了获取AOP代理对象的方法，其实现类需要实现该方法以返回代理对象。
 *
 * AOP代理接口
 * 用于获取AOP代理对象
 * getProxy方法返回代理对象
 *
 * @author yihua.huang@dianping.com
 */
public interface AopProxy {

    /**
     * 获取AOP代理对象
     * @return AOP代理对象
     */
    Object getProxy();
}
