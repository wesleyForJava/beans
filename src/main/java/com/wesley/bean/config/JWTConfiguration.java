package com.wesley.bean.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wesley.bean.interceptor.JwtTokenInterceptor;


@Configuration
public class JWTConfiguration extends WebMvcConfigurerAdapter{


}
