package com.wesley.bean.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.wesley.bean.pojo.WisleyMessage;
import com.wesley.bean.pojo.WisleyResponse;

/**
 * 编写通讯交互的控制器
 * 以及添加配置WebSocket的请求路径
 * @author pc
 * @MessageMapping 注解开启WebSocket的访问地址映射
 */
@Controller
public class WsController {
	/**
	 * 当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址，
	 * 类似@RequestMapping，当服务端有消息存在时，
	 * 会对订阅@SendTo中路径的浏览器发送请求。
	 * 接下来我们添加一个Jsp页面来配置WebSocket的JS使用方式。
	 * @param message
	 * @return
	 * @throws Exception
	 */
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WisleyResponse say(WisleyMessage message) throws Exception{
		//等待三秒返回消息内容
		Thread.sleep(3000);
		return new WisleyResponse(message.getName());
	}

}
