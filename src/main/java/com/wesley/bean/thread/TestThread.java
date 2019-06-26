package com.wesley.bean.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestThread extends Thread{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"\tI am child Thread...");
	}
	
	
	
	
  public static class CallerTask implements Callable<String>{

		@Override
		public String call() throws Exception {
			return "hello!";
		}
	  
  }
	
  public static void main(String[] args) {
	  
	  //创建异步任务
	  FutureTask<String> future=new FutureTask<String>(new CallerTask());

      //启动线程
	  new Thread(future).start();
	  
	  //等待任务执行完毕
	  try {
		String result = future.get();
	} catch (InterruptedException e) {
		e.printStackTrace();
	} catch (ExecutionException e) {
		e.printStackTrace();
	}
	  
	  
  }
	
	
}
