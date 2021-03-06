package com.wesley.bean.thread;


public class ThreadLocalTest {

	
	//1.print函数
	static void print(String str) {
		//1.1打印当前线程中本地内存中localVariable变量的值
		System.out.println(str+""+localVariable.get());
		//1.2清除当前线程中本地内存中的localVariable变量
		localVariable.remove();
	}
	
	//2.创建ThreadLocal变量
	static ThreadLocal<String> localVariable = new ThreadLocal<>();
	
	
	
	public static void main(String [] args) {
		//3.创建线程One
		Thread threadOne=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//3.1 设置One中本地变量LocalVariable的值
				localVariable.set("threadOne local variable");
				//3.2 调用打印函数
				print("threadOne");
				//3.3 打印本地变量值
				System.out.println("threadOne remove after:"+localVariable.get());
				
			}
		});
		//4.创建线程Two
		Thread threadTwo=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//4.1 设置One中本地变量LocalVariable的值
				localVariable.set("threadTwo local variable");
				//4.2 调用打印函数
				print("threadTwo");
				//4.3 打印本地变量值
				System.out.println("threadTwo remove after:"+localVariable.get());
				
			}
		});
		
		//5.启动线程
		threadOne.start();
		threadTwo.start();
	}
}
