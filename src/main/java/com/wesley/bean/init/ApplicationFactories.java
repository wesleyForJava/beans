package com.wesley.bean.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationFactories implements ApplicationContextInitializer<ConfigurableApplicationContext>{


	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.out.println("初始化第三种方式:"+applicationContext.getApplicationName());
	}

}
