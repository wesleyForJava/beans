package com.wesley.bean.test;

public class TestJoin {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("start");
		
		Thread t=new Thread(()->{
			for (int i = 1; i <= 10; i++) {
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
      t.start();
      t.join();
      System.out.println("end...");
	}
}
