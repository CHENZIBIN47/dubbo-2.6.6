package com.alibaba.dubbo.schema;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SchemaConfig implements ApplicationListener<ContextRefreshedEvent>{
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//实现了ApplicationListener spring容器加载bean完成后进行初始化调用onApplicationEvent()
		System.out.println("init"); 
	}
	
	

}
