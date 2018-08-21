package com.wesley.bean.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.pojo.UserEntity;
import com.wesley.bean.service.UserEntityService;

@RestController
public class UserRedisController {
	
	@Autowired
	UserEntityService userEntityService;
	
	/**
	 * 查询用户列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public List<UserEntity> list(){
		return userEntityService.list();
		
	}

}
