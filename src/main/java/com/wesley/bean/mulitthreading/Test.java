package com.wesley.bean.mulitthreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
  public static void main(String[] args) {
//		LongAdder longAdder=new LongAdder();
//		LongAccumulator longAccumulator=new LongAccumulator(new LongBinaryOperator() {
//			
//			@Override
//			public long applyAsLong(long left, long right) {
//				return left*right;
//			}
//		}, 10);
//	  longAdder.add(1);
//	  longAdder.add(2);
//		System.out.println(longAdder.sum());
//		longAccumulator.accumulate(1000);
//		System.out.println(longAccumulator.get());
//		CopyOnWriteArrayList<String> copyOnWriteArrayList=new CopyOnWriteArrayList<String>();
//		copyOnWriteArrayList.add("1");
//		copyOnWriteArrayList.add("2");
//		copyOnWriteArrayList.set(1, "3");
//		Iterator<String> iterator = copyOnWriteArrayList.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
	  ReentrantLock lock=new ReentrantLock();
	  Condition condition = lock.newCondition();
	  lock.lock(); //3
          try {
        	System.out.println("begin await");
			condition.await();//4
			System.out.println("begin await");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock(); //5
		}
          lock.lock(); //6
          try {
        	  System.out.println("begin await");
        	  condition.signal();//7
        	  System.out.println("begin await");
          } catch (Exception e) {
        	  e.printStackTrace();
          }finally {
        	  lock.unlock(); //8
          }
          
	  
}
}
