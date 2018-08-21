package com.wesley.bean.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.jpa.UserEntityJpa;
import com.wesley.bean.pojo.UserEntity;

@RestController
@RequestMapping(value="/userentity")
public class LoginController {
    
	@Autowired
	private UserEntityJpa userEntityJpa;
	
	@RequestMapping(value="/login",produces="text/html;charset=UTF-8")
	public String login(UserEntity user,HttpServletRequest request) {
		//登录成功
		boolean flag=true;
		String result="登录成功";
		//根据用户名查询用户是否存在
		UserEntity userEntiy = userEntityJpa.findOne(new Specification<UserEntity>() {
			
			@Override
			public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get("name"), user.getName()));
				return null;
			}
		});
		//用户不存在
		if(userEntiy==null) {
			flag=false;
			result="用户不存在,登录失败！";
		}else if(!userEntiy.getPwd().equals(user.getPwd())) {
			flag=false;
			result="密码错误,登录失败！";
		}
		if(flag) {
			request.getSession().setAttribute("_session_user", userEntiy);
		}
		return result;
	}
	
	
}
