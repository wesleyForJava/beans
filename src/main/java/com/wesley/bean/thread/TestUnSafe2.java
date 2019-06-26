package com.wesley.bean.thread;

import java.lang.reflect.Field;

import sun.misc.Unsafe;
public class TestUnSafe2 {
	static final Unsafe unsafe;
	static final long stateOffset;
	private  volatile long state=0;
	static {
		try {
			//使用反射获取UnSafe的成员变量theUnsafe
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			//设置为可存取
			field.setAccessible(true);
				//获取该变量的值
				unsafe = (Unsafe) field.get(null);
				//获取state在TestUnSafe2中的偏移量
				stateOffset = unsafe.objectFieldOffset(TestUnSafe2.class.getDeclaredField("state"));
				
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage()) ;
			throw new Error(ex);
		}
	}
	
	public static void main(String[] args) {
		TestUnSafe2 test=new TestUnSafe2();
		 boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
		 System.out.println(success);
	}
	
	
	
}
