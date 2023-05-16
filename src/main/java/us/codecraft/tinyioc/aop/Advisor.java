package us.codecraft.tinyioc.aop;

import org.aopalliance.aop.Advice;

/**
 * 切面通知器接口，用于获取通知Advice对象
 */
public interface Advisor {

    /**
     * 获取Advice对象
     * @return Advice对象
     */
    Advice getAdvice();
}
