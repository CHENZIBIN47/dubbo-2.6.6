package com.alibaba.dubbo.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport{

	@Override
	public void init() {
		
		registerBeanDefinitionParser("schemaConfig", new MyBeanDefinitionParser(SchemaConfig.class,true));
		
	}

	
	
}
