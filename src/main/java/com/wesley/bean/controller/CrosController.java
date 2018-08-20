package com.wesley.bean.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrosController {
		   
	
	        @RequestMapping(value = "/cors")
		    @ResponseBody
		    public String corsIndex(){
		        return "this is cors info.";
		    }
	 
	   @RequestMapping("/helloEveryBody")
	    public String helloHtml(HashMap<String, Object> map) {
	        map.put("hello", "欢迎进入HTML页面");
	        return "/index";
	    }
}
