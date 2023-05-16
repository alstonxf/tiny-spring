package us.codecraft.tinyioc.context;

import us.codecraft.tinyioc.beans.BeanDefinition;
import us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import us.codecraft.tinyioc.beans.factory.AutowireCapableBeanFactory;
import us.codecraft.tinyioc.beans.io.ResourceLoader;
import us.codecraft.tinyioc.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**

 基于ClassPath的Xml类型的ApplicationContext实现类
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

	/**
	 * 导入需要使用的包。
	 * 定义了一个基于ClassPath的Xml类型的ApplicationContext类，继承自AbstractApplicationContext抽象类。
	 */
	//配置文件路径
	private String configLocation;

	//	定义了一个字符串类型的configLocation变量，表示配置文件的路径
	public ClassPathXmlApplicationContext(String configLocation) throws Exception {
		//这是一个构造函数的语法， this(configLocation, new AutowireCapableBeanFactory()); 调用了 ClassPathXmlApplicationContext类的另一个构造函数，并将传递给它的参数是 configLocation 和 new AutowireCapableBeanFactory()。这种语法可以在一个构造函数中调用另一个构造函数，以便避免在类中重复代码。
		this(configLocation, new AutowireCapableBeanFactory());
	}

	//定义了一个构造方法，传入配置文件的路径，创建一个AutowireCapableBeanFactory对象并调用下一个构造方法。
	public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
		super(beanFactory);
		this.configLocation = configLocation;
		refresh();
	}

	/**
	 从XML配置文件中加载BeanDefinition，并将其注册到beanFactory中
	 定义了一个构造方法，传入配置文件的路径和AbstractBeanFactory对象，调用父类的构造方法，将AbstractBeanFactory对象传入父类的成员变量beanFactory中，并初始化configLocation，最后调用refresh()方法。
	 */
	@Override
	protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}
	}
}