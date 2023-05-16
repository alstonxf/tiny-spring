package us.codecraft.tinyioc.aop;

/**
 * @author yihua.huang@dianping.com
 *
 * 这是一个实现 AopProxy 接口的抽象类 AbstractAopProxy。它包含一个成员变量 advised，表示被代理的对象，以及一个构造方法，用于初始化该成员变量。
 *
 * 该类为 AopProxy 接口提供了一些通用的实现，可以被子类继承和扩展。在实现具体的 AOP 代理类时，子类可以重写抽象方法，并且可以通过访问 advised 变量来访问被代理的对象。
 *
 * 在 Spring 框架中，AbstractAopProxy 类是所有 AOP 代理类的基类，它实现了一些通用的功能，如代理对象的初始化和销毁等。子类只需要根据不同的代理方式，重写一些抽象方法，即可实现具体的代理逻辑。
 *
 * 这样，通过继承 AbstractAopProxy 类，就可以方便地实现 AOP 代理，并且可以在不同的代理场景中进行灵活配置和扩展。
 */

public abstract class AbstractAopProxy implements AopProxy {
    //注意：AbstractAopProxy 类虽然实现了 AopProxy 接口，但是并没有直接实现 AopProxy 接口中的方法。实际上，这是因为 AbstractAopProxy 类是一个抽象类，而非一个具体的实现类。
    // 抽象类是为了给子类提供一些通用的属性或者方法，具体的实现是交给子类去完成的。因此，子类需要实现 AopProxy 接口中的方法，而不是 AbstractAopProxy 类本身。
    /**
     * 被代理的对象
     */
    protected AdvisedSupport advised;

    /**
     * 构造方法，初始化被代理对象
     * @param advised 被代理的对象
     */
    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
