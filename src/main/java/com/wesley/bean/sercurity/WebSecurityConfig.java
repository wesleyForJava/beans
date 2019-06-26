package com.wesley.bean.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Bean
	public UserDetailsService customerUserService() {
		return new CustomerUserService();
	}
	
	//请求授权
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ROLE_ADMIN")
		.antMatchers("/user/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
		.anyRequest().authenticated();
		
		http.formLogin() //通过formLogin方法制作登录操作
		.loginPage("/login")//使用loginPage定制登录页面的访问地址
		.defaultSuccessUrl("/success")//登录成功后转向的页面
		.failureUrl("/login?error")//登录失败后转向的页面
		.permitAll()
		.and()
		.rememberMe()//开启cookie存储用户信息
		.tokenValiditySeconds(120000)//指定cookie的有效期为12000s
		.key("mykey") //指定cookie中的私钥
		.and()
		.logout() //使用logout方法定制登出行为
		.logoutUrl("/custom-logout") //指定注销的url路径
		.logoutSuccessUrl("/logout-success") //注销成功后转向的页面
		.permitAll();
	}
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customerUserService());
		super.configure(auth);
	}



	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
	
	

}
