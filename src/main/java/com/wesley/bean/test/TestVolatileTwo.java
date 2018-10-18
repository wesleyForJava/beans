package com.wesley.bean.test;

public class TestVolatileTwo {

	private static volatile int n=0;
	
	
	private static synchronized void add() {
		n++;
	}
	
	public static void main(String[] args) {
		new MyThread().start();
		while(n<100) {
		}
		System.out.println("退出");
	}
	
	static class MyThread extends Thread{
		@Override
		public void run() {
			
			try {
				super.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 200; i++) {
				   add();
			}
		}
		
		
		
		
	}
	
	
}
