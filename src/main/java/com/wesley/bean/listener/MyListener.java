package com.wesley.bean.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
/**
 * 还可以把这个类配置到META-INF/spring.factories 里面
 * 入口类就可以不用添加监听事件了。
 * 那就是改用配置的方式了。
 * @author Administrator
 *
 */
public class MyListener implements ApplicationListener{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
      System.out.println("我正在监听这个事件！"+event);		
	}
	
	

}
