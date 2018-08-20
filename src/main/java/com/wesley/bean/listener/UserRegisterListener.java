package com.wesley.bean.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.wesley.bean.bean.UserBean;
import com.wesley.bean.event.UserRegisiterEvent;
import com.wesley.bean.service.UserService;
@Component
public class UserRegisterListener implements SmartApplicationListener {
    /**
     *  supportsEventType & supportsSourceType 两个方法返回true时调用该方法执行业务逻辑
     * @param applicationEvent 具体监听实例，这里是UserRegisterEvent
     */
    @Async
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	UserRegisiterEvent eRegisiterEvent=(UserRegisiterEvent)event;
    	UserBean userBean = eRegisiterEvent.getUserBean();
        System.out.println("用户："+userBean.getName()+"，注册成功，发送邮件通知。");
	}

	@Override
	public int getOrder() {
		return 0;
	}
    
	/**
     *  该方法返回true&supportsSourceType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param eventType 接收到的监听事件类型
     * @return
     */
	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		//只有UserRegisterEvent监听类型才会执行下面逻辑
		return eventType == UserRegisiterEvent.class;
	}
	 /**
     *  该方法返回true&supportsEventType同样返回true时，才会调用该监听内的onApplicationEvent方法
     * @param sourceType
     * @return
     */
	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		 //只有在UserService内发布的UserRegisterEvent事件时才会执行下面逻辑
        return sourceType == UserService.class;
	}

}
