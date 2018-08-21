package com.wesley.bean.listener;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class FailedEvent extends ApplicationFailedEvent{
	private static final long serialVersionUID = 1L;

	public FailedEvent(SpringApplication application, String[] args, ConfigurableApplicationContext context,
			Throwable exception) {
		super(application, args, context, exception);
		System.out.println("如果启动时出现异常，则发送ApplicationFailedEvent。");
	}



}
