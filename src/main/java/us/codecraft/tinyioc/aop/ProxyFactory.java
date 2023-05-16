package us.codecraft.tinyioc.aop;

/**
 * 代理工厂类，继承自AdvisedSupport类，实现AopProxy接口，用于创建代理对象
 *
 * author yihua.huang@dianping.com
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

	// 创建代理对象
	@Override
	public Object getProxy() {
		return createAopProxy().getProxy();
	}

	// 创建AopProxy对象
	protected final AopProxy createAopProxy() {
		return new Cglib2AopProxy(this);
	}
}
