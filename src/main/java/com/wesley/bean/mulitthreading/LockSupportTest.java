package com.wesley.bean.mulitthreading;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
        public static void main(String[] args) throws InterruptedException {
        	Thread thread=new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("child thread  begin unpark");
//					LockSupport.park();
					while (!Thread.currentThread().isInterrupted()) {
						LockSupport.park();
					}
					System.out.println("child thread unpark");
				}
			});
         thread.start();
         
          Thread.sleep(1000);
          System.out.println("main thread begin unpark");
          //LockSupport.unpark(thread);
          thread.interrupt();
          
		}
}
