package com.wesley.bean.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class PreparedEvent  extends ApplicationPreparedEvent{
	private static final long serialVersionUID = 1L;

	public PreparedEvent(SpringApplication application, String[] args, ConfigurableApplicationContext context) {
		super(application, args, context);
		System.out.println("在刷新之后发送ApplicationReadyEvent并处理任何相关的回调，以指示应用程序已准备好为请求提供服务。");
	}

}
