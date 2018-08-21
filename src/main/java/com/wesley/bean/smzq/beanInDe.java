package com.wesley.bean.smzq;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import com.wesley.bean.init.Animal;

public class beanInDe  implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		 System.out.println("=====postProcessBeforeInitialization======"+bean.getClass());
		if(bean instanceof Animal) {
			return new Animal();
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	    if(bean instanceof StringHttpMessageConverter) {
	    	MediaType mediaType=new MediaType("text","plain",Charset.forName("UTF-8"));
	    	List<MediaType> mediaTypes=new ArrayList<>();
	    	mediaTypes.add(mediaType);
	    	((StringHttpMessageConverter) bean).setSupportedMediaTypes(mediaTypes);
	    }
		return bean;
	}

}
