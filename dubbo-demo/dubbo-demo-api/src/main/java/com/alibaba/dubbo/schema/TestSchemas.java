package com.alibaba.dubbo.schema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSchemas {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		SchemaConfig config = (SchemaConfig) context.getBean("demo");
		System.out.println(config.getUsername());
		System.out.println(config.getPassword());
	}

}
