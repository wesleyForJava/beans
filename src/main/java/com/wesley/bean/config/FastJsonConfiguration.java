package com.wesley.bean.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
@Configuration
public class FastJsonConfiguration extends WebMvcConfigurerAdapter {
	/**
	 * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
	 *	WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
	 *	DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
	 *	WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
	 *	WriteMapNullValue：是否输出值为null的字段,默认为false。
	 */

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		
		//创建fastJson消息转换器
		FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
		//创建配置类
		FastJsonConfig fastJsonConfig =new  FastJsonConfig();
		//修改配置内容的过滤
		fastJsonConfig.setSerializerFeatures(
				SerializerFeature.DisableCircularReferenceDetect,//消除循环引用	
				SerializerFeature.WriteMapNullValue,//是否输出值为null的字段,默认为false 
				SerializerFeature.WriteNullStringAsEmpty
				);
		converter.setFastJsonConfig(fastJsonConfig);
		converters.add(converter);
	}


}
