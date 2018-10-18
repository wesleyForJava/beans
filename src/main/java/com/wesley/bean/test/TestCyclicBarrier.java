package com.wesley.bean.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
	
	
	public static void main(String[] args) {
		CyclicBarrier cb=new CyclicBarrier(5,()->{
			System.out.println("cd finish...");
		});
		
	  long start=System.currentTimeMillis();
	  for (int i = 0; i < 20; i++) {
		new Thread(()->{
			try {
				Thread.sleep(1000);
				cb.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(String.format("耗时: %s ms", System.currentTimeMillis()-start));
		}).start();
	}
	  
  }
}
