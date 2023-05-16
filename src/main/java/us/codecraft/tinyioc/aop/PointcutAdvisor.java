package us.codecraft.tinyioc.aop;

/**
 * 定义了一个切点增强器接口，继承自Advisor接口，包含获取切点的方法
 *
 * author yihua.huang@dianping.com
 */
public interface PointcutAdvisor extends Advisor {

   // 获取切点
   Pointcut getPointcut();
}
