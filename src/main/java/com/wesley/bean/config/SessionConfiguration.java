package com.wesley.bean.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wesley.bean.interceptor.SessionInterceptor;
@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/bean/static/**")
		.excludePathPatterns("/test*")
		.excludePathPatterns("/upload*")
		.excludePathPatterns("/detail*/*")
		.excludePathPatterns("/user*/**")
		.excludePathPatterns("/query*/**")
		.excludePathPatterns("/validator*/**")
		.excludePathPatterns("/list*/**")
		.excludePathPatterns("/login*/**")
		.excludePathPatterns("/endpointWisely*/**/**/**")
		.excludePathPatterns("/topic/getResponse*/**")
		.excludePathPatterns("/welcome**")
		.excludePathPatterns("/socket/**");
	}

	//全局解决responseBody中文乱码问题	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	  StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
	  converters.add(0, stringConverter);
	}

/*	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		   FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
	        FastJsonConfig fastJsonConfig = new FastJsonConfig();
	        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	        //处理中文乱码问题
	        List<MediaType> fastMediaTypes = new ArrayList<>();
	        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
	        fastConverter.setSupportedMediaTypes(fastMediaTypes);
	        fastConverter.setFastJsonConfig(fastJsonConfig);
	        converters.add(fastConverter);
	}*/
 
	
/*		@Override  //此方法不可行
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(responseBodyConverter());
	}*/
	 
/*	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
	    StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
	    return converter;
	}*/
	
/*	@Bean  也试过这种方法，不可行
	public HttpMessageConverter<String> responseBodyConverter(){
	StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
	return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	super.configureMessageConverters(converters);

	converters.add(responseBodyConverter());
}
 */}
