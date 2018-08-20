package com.wesley.bean.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.pojo.DemoEntity;

@RestController
public class IndexController {
	@Autowired
	private MessageSource messageSource;  //这里用于格式化错误消息
	
	 @RequestMapping(value = "/validator")
	public String validator(@Valid DemoEntity entity,BindingResult result) {
		
		if(result.hasErrors()) {
			StringBuffer msg=new StringBuffer();
			//获取错误字段的集合
			List<FieldError> fieldErrors = result.getFieldErrors();
			//hibernate-validator支持国际化
			Locale currentLocale = LocaleContextHolder.getLocale();
			
			//遍历错误字段获取错误消息
			for (FieldError fieldError : fieldErrors) {
				String errorMessage = messageSource.getMessage(fieldError, currentLocale);
				msg.append(fieldError.getField()+":"+errorMessage+",");
			}
			return msg.toString();
		}
		return "验证通过！"+entity.getName()+"\r\n"+entity.getPassword()+"\r\n"+entity.getEmail();
	}

	 @RequestMapping(value = "/api")//拦截请求是如果里面添加了/api/context 这样是没有层级的,必须有层级的才能拦截
	 public String index() {
		return "success";
	 }
	 
	 
}
