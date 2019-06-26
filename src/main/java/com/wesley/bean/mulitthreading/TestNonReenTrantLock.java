package com.wesley.bean.mulitthreading;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;


//2.使用自定义锁实现生产一消费模型下面我们使用上节自定义的锁实现一个简单的生产一消费模型，代码如下。
public class TestNonReenTrantLock {
	
	final static NonRenntranLock lock=new NonRenntranLock();
	final static Condition notFull = lock.newCondition();
	final static Condition notEmpty = lock.newCondition();
	final static Queue<String> queue=new LinkedBlockingDeque<String>();
	final static int queueSize=10;
	
	public static void main(String[] args) {
		
		Thread producer=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//获取独占锁
				lock.lock();
				try {
				//1.如果队列满了则等待
				while(queue.size()==queueSize) {
						notEmpty.await();
				}
				//2.添加元素到队列
				queue.add("ele");
				
				//3.唤醒消费线程
				notFull.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					//释放锁
					lock.unlock();
				}
			}
		});
		
		Thread consumer=new Thread(new Runnable() {
			@Override
			public void run() {
				//获取独占锁
				lock.lock();
				try {
				//队列空则等待
				while(0==queue.size()) {
					notFull.await();
				}
				
				//消费一个元素
				String ele = queue.poll();
				System.out.println(ele);
				//唤醒生产线程
				notEmpty.signalAll();
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					//释放锁
					lock.unlock();
				}
			}
		});
		
		producer.start();
		consumer.start();
		
		
		
		
		
	}
//	如上代码首先创建了NonReentrantLock的一个对象lock，然后调用lock.newCondition创建了两个件变量，
//	用来进行生产者和消费者线程之间的同步。
//
//	在main函里面，首先创建了producer生产线程，在线程内部首先调用lock.lock()获取独占锁，
//	然后判断当前队列是否己经满了，如果满了则调用notEmpty.await()阻塞挂起当前线程。
//	需要注意的是，这里使用while而不是if是为了避免虚假唤醒。如果队列不满则直接向队列里面添加元素，
//	然后调用notFull.signalAll()唤醒所有因为消费元素而被阻塞的消费线程，最后释放获取的锁。
//
//	然后在main函数里面创建了consumer生产线程，在线程内部首先调用lock.lock()获取独占锁，
//	然后判断当前队列里面是不是有元素，如果队列为空则调用notFull.await()阻塞挂起当前线程。
//	需要注意的是，这里使用while而不是if是为了避免虚假唤醒。如果队列不为空则直接从队列里面获取并移除元素，
//	然后唤醒因为队列满而被阻塞的生产线程，最后释放获取的锁。


}
