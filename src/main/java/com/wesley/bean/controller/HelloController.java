package com.wesley.bean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/typography")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello() {
		return "typography";
	}
	 @GetMapping("/ztree")
	 public String lookZtree() {
		 return "ztree";
	 }
	
}
