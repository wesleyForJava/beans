package com.wesley.bean.init;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;
@Component
public class Animal {
	@PostConstruct
	public void intial() {
		System.out.println("bean初始化");
	}
   
	@PreDestroy
	public void destory() {
		System.out.println("执行初始化结束");
	}
	
}
