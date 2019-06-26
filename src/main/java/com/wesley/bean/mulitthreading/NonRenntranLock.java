package com.wesley.bean.mulitthreading;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class NonRenntranLock implements Lock,Serializable{
	private static final long serialVersionUID = 1L;

	//内部帮助类
	static class Sync extends AbstractQueuedSynchronizer{
		private static final long serialVersionUID = 1L;

		//是否锁已经被持有
		@Override
		protected boolean isHeldExclusively() {
			return getState()==1;
		}
		
		//如果state=0则尝试获取锁
		@Override
		protected boolean tryAcquire(int acquires) {
			assert acquires==1;
			if(compareAndSetState(0, 1)){
				setExclusiveOwnerThread(Thread.currentThread());
			}
			return false;
		}
        
		//尝试释放锁，设置state为0
		@Override
		protected boolean tryRelease(int release) {
			assert release==1;
			if(getState()==0) {
				throw new IllegalMonitorStateException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		
		//提供条件变量接口
	   public	Condition newCondition() {
			return new ConditionObject();
		}
		
	}
	
	//创建一个具体Sync来做具体的工作
	public final Sync sync=new Sync();

	@Override
	public void lock() {
		sync.acquire(1);
	}
	
	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public void unlock() {
		sync.release(1);
	}
	
	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
	  
	 public boolean isLocked() {
		return sync.isHeldExclusively();
	}
	
	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}


	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}
	//	在如上代码中，NonReentrantLock定义了一个内部类Sync用来实现具体的锁的操作，Sync则继承了AQS。
	//	由于我们实现的是独占模式的锁，所以Sync重写了tryAcquire、tryRelease、isHeldExclusively3个方法。
	//	另外，Sync提供了newCondition这个方法用来支持条件变量。
}
