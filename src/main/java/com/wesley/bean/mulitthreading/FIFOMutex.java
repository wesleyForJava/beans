package com.wesley.bean.mulitthreading;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;


public class FIFOMutex {
	
	private final AtomicBoolean locked=new AtomicBoolean(false);

	private final Queue<Thread> writers=new ConcurrentLinkedDeque<Thread>();
	
	
	public void lock() {
		boolean wasInterrupted=false;
		Thread cThread = Thread.currentThread();
		writers.add(cThread);
		
		//只有队首的线程可以获取锁
		while(writers.peek()!=null || !locked.compareAndSet(false, true)) {
			LockSupport.park(this);
			if(Thread.interrupted()) {
				wasInterrupted=true;
			}
		};
		writers.remove();
		
		if(wasInterrupted) {
			cThread.interrupt();
		}
	}
	
	public void unlock() {
		locked.set(false);
		LockSupport.unpark(writers.peek());
	}
}
