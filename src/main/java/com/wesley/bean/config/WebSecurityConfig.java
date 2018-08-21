package com.wesley.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.wesley.bean.service.UserAuthService;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	UserDetailsService usesService() {
		return new UserAuthService();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.anyRequest().authenticated()//所有请求必须登录后访问
		.and()
		.formLogin()
		.loginPage("/login")
		.failureUrl("/login?error")
		.and()
		.logout()
		.permitAll();//注销请求可直接访问
	}

}
