package com.wesley.bean.listener;

import org.springframework.context.ApplicationListener;
import com.wesley.bean.bean.UserBean;
import com.wesley.bean.event.UserRegisiterEvent;
//@Component
public class RegisterListener implements ApplicationListener<UserRegisiterEvent>{

	@Override
	public void onApplicationEvent(UserRegisiterEvent event) {
		UserBean userBean = event.getUserBean();
		System.out.println("监听到的信息如下："+userBean.getName());
	}

}
