package com.alibaba.dubbo.demo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionFactory;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.extension.SPI;
import com.alibaba.dubbo.demo.DemoService;

public class SPITest {
	
	public static void main(String[] args) {
		ExtensionLoader<DemoService> loader = ExtensionLoader.getExtensionLoader(DemoService.class);
//		DemoService demoService = loader.getExtension("demo3");
//		demoService.sayHello("spi");
		URL url = URL.valueOf("test://localhost/test?t=demo3");
		DemoService demoService = loader.getAdaptiveExtension();
		demoService.sayHello("spi",url);
	}
	
	/**
	 * 总结：dubbo的SPI和adaptive注解
	 * 		adaptive目的是为了识别固定已知类和扩展未知类，Adaptive注解得跟SPI注解结合使用
	 * 			1、当adaptive注解在实现类上 表示人工实现了一个装饰类 固定扩展类 优先级最高
	 * 			2、当Adaptive注解在接口的方法上表示这是一个动态代理类，并且方法参数必须有URL 没有的话将会报错（Caused by: java.lang.IllegalStateException: Can not create adaptive extension interface com.alibaba.dubbo.demo.DemoService, cause: fail to create adaptive class for interface com.alibaba.dubbo.demo.DemoService: not found url parameter or url attribute in parameters of method sayHello）
	 * 				@Adaptive注解在方法上时
	 * 					1） 当url没有对应参数时 例如（test://localhost/test ） 并且@SPI注解也没有对应的值  将会报错（Fail to get extension(com.alibaba.dubbo.demo.DemoService) name from url(test://localhost/test) use keys([demo.service])）
	 * 					2） 当url没有对应参数时 @SPI有对应的值（@SPI("demo2")）则会使用@SPI注解中的值作为扩展
	 * 					3）当url有对应参数时例如（test://localhost/test?demo.service=demo3）时，不管@SPI注解有没有值 都将会使用url参数的值作为扩展
	 * 					4)当@Adaptive注解有指定值时（@Adaptive({"t"})） 则以参数t为准（既t参数没有的话也不会使用默认的key来作为扩展）作为扩展  test://localhost/test?t=demo3
	 * 		优先级大致如下： @Adaptive注解在实现类 > @Adaptive注解在方法上且有值（ @Adaptive({"t"}）,url参数key为对应的值  > @Adaptive注解在方法上并且url参数有默认的key > @Adaptive注解在方法上并且url的默认参数没有值 @SPI注解上有值
	 *   
	 *   
	 *   
	 *   SPI源码分析：
	 *   	com.alibaba.dubbo.common.extension.ExtensionLoader.getExtensionLoader(Class<T>)//getExtensionLoader 方法用于从缓存中获取与拓展类对应的 ExtensionLoader，若缓存未命中，则创建一个新的实例
	 *   		objectFactory = (type == ExtensionFactory.class ? null : ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension());
	 *   	执行步骤：1) new ExtensionLoader<T>(type) -> 2) ExtensionLoader.getExtensionLoader(ExtensionFactory.class)
	 *   				-> 3)new ExtensionLoader<T>(ExtensionFactory) objectFactory=null 把ExtensionFactory实例放到缓存中 -> 4) getAdaptiveExtension()
	 *   
	 *   		getAdaptiveExtension()
	 *   			createAdaptiveExtension();
	 *   				injectExtension((T) getAdaptiveExtensionClass().newInstance()) 
	 *   					getAdaptiveExtensionClass()
	 *   						getExtensionClasses() //加载配置文件中所有要扩展的实现类
	 *   							loadExtensionClasses()
	 *   						
	 *   	com.alibaba.dubbo.common.extension.ExtensionLoader.getExtension(String)
	 *   		com.alibaba.dubbo.common.extension.ExtensionLoader.createExtension(String)//创建扩展实例
	 *   			 Class<?> clazz = getExtensionClasses().get(name);//getExtensionClasses()从配置文件中加载所有的扩展类 clazz为对应要扩展的实现类
	 *   			// 通过反射创建实例 
	                 EXTENSION_INSTANCES.putIfAbsent(clazz, clazz.newInstance());
	                 instance = (T) EXTENSION_INSTANCES.get(clazz); //instance 为对应要扩展实现类的实例
	 * 	
	 * 	Adaptive源码分析：
	 * 		loader.getAdaptiveExtension();
	 * 			getAdaptiveExtension()
	 * 				createAdaptiveExtension();
	 * 					getAdaptiveExtensionClass()
	 * 						createAdaptiveExtensionClass()
	 * 							createAdaptiveExtensionClassCode()////创建adaptive扩展类代码
	 * 		创建的代码如下：
	 * 				package com.alibaba.dubbo.demo;
	 *				import com.alibaba.dubbo.common.extension.ExtensionLoader;
	 *				public class DemoService$Adaptive implements com.alibaba.dubbo.demo.DemoService {
	 *				public java.lang.String sayHello(java.lang.String arg0, com.alibaba.dubbo.common.URL arg1) {
	 *					if (arg1 == null) throw new IllegalArgumentException("url == null");
	 *					com.alibaba.dubbo.common.URL url = arg1;
	 *					String extName = url.getParameter("t");
	 *					if(extName == null) throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.demo.DemoService) name from url(" + url.toString() + ") use keys([t])");
	 *					com.alibaba.dubbo.demo.DemoService extension = (com.alibaba.dubbo.demo.DemoService)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.demo.DemoService.class).getExtension(extName);
	 *					return extension.sayHello(arg0, arg1);
	}
	 * 
	 * 				   
	 */

	
	
}
