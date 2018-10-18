package com.wesley.bean.test;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl =new CountDownLatch(10);
		long start=System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			new Thread(()-> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
				  cdl.countDown();
				}
			}).start();
		}
     cdl.await();
     long count = cdl.getCount();
     System.out.println(count);
     System.out.println(String.format("耗时:%s ms", System.currentTimeMillis()-start));
	}
}
