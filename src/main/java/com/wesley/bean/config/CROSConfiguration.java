package com.wesley.bean.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 跨域配置
 * @author pc
 *
 */
@Configuration
public class CROSConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
		registry.addMapping("/**")
		.allowedHeaders("*")
		.allowedOrigins("*")
		.allowedMethods("GET","POST");
	}

}
