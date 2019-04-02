package com.alibaba.dubbo.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class MyBeanDefinitionParser implements BeanDefinitionParser{
	
	private final Class<?> beanClass;
    private final boolean required;
	 public MyBeanDefinitionParser(Class<?> beanClass, boolean required) {
        this.beanClass = beanClass;
        this.required = required;
    }

	private static BeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass, boolean required) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		String username = element.getAttribute("username");
		String password = element.getAttribute("password");
		String id = element.getAttribute("id");
		parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
		beanDefinition.getPropertyValues().addPropertyValue("username", username);
		beanDefinition.getPropertyValues().addPropertyValue("password", password);
		return beanDefinition;
	}
	
	public BeanDefinition parse(Element element, ParserContext parserContext) {
        return parse(element, parserContext, beanClass, required);
    }

}
