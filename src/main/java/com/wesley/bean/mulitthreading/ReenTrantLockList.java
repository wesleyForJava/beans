package com.wesley.bean.mulitthreading;

import java.util.ArrayList;

public class ReenTrantLockList {
	
	
	//线程不安全的list
	private ArrayList<String> array=new ArrayList<String>();
	
	//独占锁
	private volatile ReentrantLock1 lock=new ReentrantLock1();
	
	
	//添加元素
	public void add(String e) {
      lock.lock();
      try {
		array.add(e);
	} catch (Exception e2) {
		e2.printStackTrace();
	}finally {
		lock.unlock();
	}
      
	}
	//删除元素
	public void remove(String e) {
		lock.lock();
		try {
			array.remove(e);
		} catch (Exception e2) {
			e2.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
	//获取元素
	public String get(int index) {
		lock.lock();
		try {
			return	array.get(index);
		} catch (Exception e2) {
			e2.printStackTrace();
			return null;
		}finally {
			lock.unlock();
		}
		
	}
	

}
