package com.wesley.bean.thread;

public class DeamonTest {
	
	public static void main(String[] args) {
		Thread thread=new Thread(new Runnable() {
			public void run() {
				for (;;) {
					
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
		System.out.println("main thread is over");
		
	}

}
