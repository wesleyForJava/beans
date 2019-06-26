package com.wesley.bean.mulitthreading;

import java.util.concurrent.locks.LockSupport;

public class TestPark {

	
	 public void park() {
//		 LockSupport.park(this);
		 System.out.println("开始阻塞");
		 LockSupport.parkNanos(2000);
		 System.out.println("输出");
	 }
	 public static void main(String[] args) {
		 TestPark testPark=new TestPark();
		 testPark.park();
	}
}
