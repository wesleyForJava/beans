package com.wesley.bean.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfiguration {
	@Bean
	public ServletRegistrationBean setviewservlet() {
		ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		//设置ip白名单
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		//设置ip黑名单
		servletRegistrationBean.addInitParameter("deny", "202.105.139.140");
		//设置用户名
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		//设置密码
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		// 禁用HTML页面上的“Reset All”功能  
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean statFilter() {
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());
		//设置过滤器路径
		filterRegistrationBean.addUrlPatterns("/*");
		//忽略过滤器的形式
	      filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
		
	}
	
	

}
