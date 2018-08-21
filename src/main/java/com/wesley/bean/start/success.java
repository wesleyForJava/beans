package com.wesley.bean.start;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Order(2)
public class success implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		System.out.println("系统成功启动！"+LocalDateTime.now());
	}

}
