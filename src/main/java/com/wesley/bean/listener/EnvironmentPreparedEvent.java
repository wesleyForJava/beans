package com.wesley.bean.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.core.env.ConfigurableEnvironment;

public class EnvironmentPreparedEvent extends ApplicationEnvironmentPreparedEvent{
	private static final long serialVersionUID = 1L;

	public EnvironmentPreparedEvent(SpringApplication application, String[] args, ConfigurableEnvironment environment) {
		super(application, args, environment);
		System.out.println("当在上下文中使用的环境是已知的但在创建上下文之前发送ApplicationEnvironmentPreparedEvent。");
	}

}
