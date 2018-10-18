package com.wesley.bean.test;

public class TestVolatileThree {
	private static volatile int n=0;
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				n=1;
			}
		}).start();
		while (n==0) {
		}
		System.out.println("test  end....");
	}
}
