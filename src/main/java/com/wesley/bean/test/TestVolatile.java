package com.wesley.bean.test;

public class TestVolatile {
   
	public static volatile boolean isStop=false;
	
	
	public static void stopit() {
		isStop=true;
	}
	
	public static void main(String[] args) {
	    new MyThread().start();
		while(!isStop) {
		}
		System.out.println("stop!");
	}
	
	
	static class MyThread extends Thread{
		@Override
		public void run() {
			try {
				super.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stopit();
		}
	}
	
	
	
	
	
}
