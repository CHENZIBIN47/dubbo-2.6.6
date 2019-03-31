package com.alibaba.dubbo.demo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.demo.DemoService;

public class SPITest {
	
	public static void main(String[] args) {
		ExtensionLoader<DemoService> loader = ExtensionLoader.getExtensionLoader(DemoService.class);
		DemoService demoService = loader.getExtension("demo2");
		demoService.sayHello("spi");
	}

}
