package com.wesley.bean.listener;

import org.springframework.stereotype.Component;
import com.wesley.bean.bean.UserBean;
import com.wesley.bean.event.UserRegisiterEvent;
/**
 * 注解方式实现监听
 * @author pc
 */
@Component
public class AnnotationRegisterListener {

	
	//@EventListener
	public void register(UserRegisiterEvent userRegisiterEvent) {
		UserBean userBean = userRegisiterEvent.getUserBean();
         System.out.println("监听到的事件：信息"+userBean.getName());		
	}

}
