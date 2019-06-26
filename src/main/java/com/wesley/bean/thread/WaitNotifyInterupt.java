package com.wesley.bean.thread;

public class WaitNotifyInterupt {
	static Object obj = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (obj) {
					try {
						System.out.println("-----begin-----------");
						obj.wait();
						System.out.println("-----end----------");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
		thread.sleep(1000);
		System.out.println("thread_Interrupt start...");
		thread.interrupt();
		System.out.println("thread_Interrupt end...");
	}
}
