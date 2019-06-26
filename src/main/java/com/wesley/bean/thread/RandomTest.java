package com.wesley.bean.thread;

import java.util.concurrent.ThreadLocalRandom;

public class RandomTest {
	
      public static void main(String[] args) {
    	  //10.获取一个随机数生成器
		ThreadLocalRandom threadLocalRandom=ThreadLocalRandom.current();
		//11.输出10个0-5(包含0,不包含5)的随机数
		for (int i = 0; i < 10; i++) {
			System.out.println(threadLocalRandom.nextInt(5));
		}
	}
}
