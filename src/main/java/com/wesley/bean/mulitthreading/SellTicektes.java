package com.wesley.bean.mulitthreading;

public class SellTicektes implements Runnable {
	    private static int num = 100 ;
	    @Override
	    public void run() {
	        while(true) {
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            if(num > 0) {
	                System.out.println(Thread.currentThread().getName() + "正在出售第" + (num--) + "张票");
	            }
	        }
	    }
}
