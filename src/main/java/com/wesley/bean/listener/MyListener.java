package com.wesley.bean.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
/**
 * 还可以把这个类配置到META-INF/spring.factories 里面
 * 入口类就可以不用添加监听事件了。
 * 那就是改用配置的方式了。
 * @author Administrator
 *
 */
public class MyListener implements ApplicationListener{
   private static final Logger logger=LoggerFactory.getLogger(MyListener.class);
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) { // 初始化环境变量
            logger.info("==========初始化环境变量==============");
        } else if (event instanceof ApplicationPreparedEvent) { // 初始化完成
            logger.info("==========初始化完成==============");
        } else if (event instanceof ContextRefreshedEvent) { // 应用刷新
            logger.info("==========应用刷新==============");
        } else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
            logger.info("=================================");
            String server = ((ApplicationReadyEvent)event).getSpringApplication().getSources().iterator().next()
                .toString();
            logger.info("系统[{}]启动完成!!!", server.substring(server.lastIndexOf(".") + 1));
            logger.info("=================================");
        } else if (event instanceof ContextStartedEvent) { // 应用启动，需要在代码动态添加监听器才可捕获
            logger.info("==========应用启动==============");
        } else if (event instanceof ContextStoppedEvent) { // 应用停止
            logger.info("==========应用停止==============");
        } else if (event instanceof ContextClosedEvent) { // 应用关闭
            logger.info("==========应用关闭==============");
        } else {
        }	
	}
	
	

}
