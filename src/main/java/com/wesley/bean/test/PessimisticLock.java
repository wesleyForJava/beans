package com.wesley.bean.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class PessimisticLock {
	
	//悲观锁1
	public synchronized void testMethod() {
      
		for (int i = 2; i > 0; i--) {
			System.out.println(Thread.currentThread().getName()+"获取到的值"+i);
		}
	}
	////悲观锁2
	private ReentrantLock lock=new ReentrantLock();
	
	//乐观锁不演示
	private AtomicInteger atomicInteger = new AtomicInteger();  // 需要保证多个线程使用的是同一个AtomicInteger
	int incrementAndGet = atomicInteger.incrementAndGet();
	
	/*public void testMethod() {
        lock.lock();
         for (int i = 2; i > 0; i--) {
			System.out.println(Thread.currentThread().getName()+"获取到的值"+i);
		}
		lock.unlock();
	}*/
   public static void main(String[] args) {
	   new Thread(()->new PessimisticLock().testMethod()).start();
	   new Thread(()->new PessimisticLock().testMethod()).start();
	   int a= 1 & 3;
	   System.out.println(a);
  }
	
	 
	
}
