package com.wesley.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wesley.bean.init.InitBeanAndDestroyBean;

@Configuration
public class MyConfig {

	@Bean(initMethod="initMethod",destroyMethod="destroyMethod")
	public InitBeanAndDestroyBean initBeanAndDestroyBean() {
		return new InitBeanAndDestroyBean();
		
	}
}
