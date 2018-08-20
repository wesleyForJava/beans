package com.wesley.bean.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user1")
public class Index1Controller {
	private final static Logger log=LoggerFactory.getLogger(Index1Controller.class);
	/**
     * 初始化登录页面
     * @return
     */
    @RequestMapping(value = "/login_view",method = RequestMethod.GET)
    public String login_view(){
    	log.info("可以输出");
    	log.debug("登录系统");
    	log.error("错误信息");
    	String succes="成功装了进去";
    	log.info("{}",succes);
        return "login";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "indexsuccess";
    }
}
