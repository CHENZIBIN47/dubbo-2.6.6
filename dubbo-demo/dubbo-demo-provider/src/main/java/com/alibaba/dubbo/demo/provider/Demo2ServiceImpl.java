package com.alibaba.dubbo.demo.provider;


import com.alibaba.dubbo.demo.DemoService;

public class Demo2ServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		System.out.println("demo2");
		return name;
	}


}
