package com.wesley.bean.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.wesley.bean.servlet.TestServlet;

@SpringBootConfiguration
//@ServletComponentScan(basePackages="com.wesley.bean.servlet")第二种方式实现servlet
public class ServletConfiguration {
	//第一种方式实现Servlet
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new TestServlet(),"/test");
	}

}
