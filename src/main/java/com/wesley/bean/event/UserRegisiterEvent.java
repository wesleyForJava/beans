package com.wesley.bean.event;

import org.springframework.context.ApplicationEvent;

import com.wesley.bean.bean.UserBean;

import lombok.Getter;
@Getter
public class UserRegisiterEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	//注册用户对象
	private UserBean userBean;
	public UserRegisiterEvent(Object source,UserBean userBean) {
		super(source);
		this.userBean=userBean;
	}

}
