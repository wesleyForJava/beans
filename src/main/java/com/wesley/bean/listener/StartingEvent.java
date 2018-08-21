package com.wesley.bean.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;

public class StartingEvent extends ApplicationStartingEvent{
	private static final long serialVersionUID = 1L;

	public StartingEvent(SpringApplication application, String[] args) {
		super(application, args);
		System.out.println("ApplicationStartingEvent在运行开始时发送，除了注册监听器和初始化器之外的任何处理之前。");
	}

}
