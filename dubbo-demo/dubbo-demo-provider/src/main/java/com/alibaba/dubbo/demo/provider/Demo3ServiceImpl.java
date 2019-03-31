package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.DemoService;

public class Demo3ServiceImpl implements DemoService{

	@Override
	public String sayHello(String name) {
		
		System.out.println("demo3");
		return name;
	}

}
