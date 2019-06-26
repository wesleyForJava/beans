package com.wesley.bean.thread;

public class sleepTest2 {

	public static void main(String[] args) throws InterruptedException {
		 Thread thread = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try{
	                    System.out.println("child thread is in sleep");

	                    Thread.sleep(10000);

	                    System.out.println("child thread is in awaked");
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	        //启动线程
	        thread.start();

	        //主线程休眠 2s
	        Thread.sleep(-2);

	        //主线程中断子线程
	        thread.interrupt();
	    }

}
