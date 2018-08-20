package com.wesley.bean.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ShareniuApplicationContextInitializer1

		implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	public void initialize(ConfigurableApplicationContext ac) {
		System.out.println("学习springboot源码分析系列文章");

	}

}
