package com.wesley.bean.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wesley.bean.util.LoggerUtils;

@RestController
@RequestMapping(value="/logger")
public class LoggerController {
	/**
	 * 简单测试请求日志的记录
	 * @param request 请求对象
	 * @param name 用户名
	 * @return
	 * @throws Exception
	 */
/*	 consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	 produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
	 value： 指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；
	 method： 指定请求的method类型， GET、POST、PUT、DELETE等；
	 params： 指定request中必须包含某些参数值是，才让该方法处理。
	 headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。*/
   @GetMapping(value="/record",produces = "text/json;charset=UTF-8")
   public JSONObject login(HttpServletRequest request,String name)throws Exception {
	   JSONObject obj=new JSONObject();
	   obj.put("msg","用户："+name+"，登录成功。");
	   request.setAttribute(LoggerUtils.LOGGER_RETURN, obj);
	return obj;
   }
}
