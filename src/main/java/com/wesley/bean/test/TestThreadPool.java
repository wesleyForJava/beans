package com.wesley.bean.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestThreadPool {
   public static void main(String[] args) {
	   //创建等待队列 
	   BlockingQueue<Runnable> blockingQueue=new ArrayBlockingQueue<Runnable>(20);
	   //创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。 
	   ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(2, 3, 2, TimeUnit.MILLISECONDS, blockingQueue);
	   Thread t1=new MyThread();
	   Thread t2=new MyThread();
	   Thread t3=new MyThread();
	   Thread t4=new MyThread();
	   Thread t5=new MyThread();
	   Thread t6=new MyThread();
	   Thread t7=new MyThread();
	   Thread t8=new MyThread();
	   
	   poolExecutor.execute(t1);
	   poolExecutor.execute(t2);
	   poolExecutor.execute(t3);
	   poolExecutor.execute(t4);
	   poolExecutor.execute(t5);
	   poolExecutor.execute(t6);
	   poolExecutor.execute(t7);
	   poolExecutor.execute(t8);
	   
	   poolExecutor.shutdown();
	   
	  


	  
  }
  static class MyThread extends Thread{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"正在执行。。。");
        try {
			super.sleep(100L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	   
   }
  
  
  interface Formula {
	    double calculate(int a);

	    default double sqrt(int a) {
	        return Math.sqrt(a);
	    }
	}
}
