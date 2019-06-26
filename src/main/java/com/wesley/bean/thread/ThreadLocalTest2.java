package com.wesley.bean.thread;

public class ThreadLocalTest2 {
	 //创建线程变量
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        //父线程中设置线程变量
        threadLocal.set("hello world");

        //启动子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//            	threadLocal.set("good");
                //子线程输出线程变量的值
                System.out.println("thread:" + threadLocal.get());
            }
        });

        thread.start();

        System.out.println("main:" + threadLocal.get());
    }
}
