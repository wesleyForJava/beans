
package com.wesley.bean.test;

import java.io.IOException;

public class DaemonThread {
  
	private static void execute() {
	  
	  for (int i = 0;; i++) {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  System.out.println(i);
	}
   }
	
	
	public static void main(String[] args) throws IOException {
		Thread thread=new Thread(()->execute());
		thread.setDaemon(true);
		thread.start();
		System.in.read();
	}
}
