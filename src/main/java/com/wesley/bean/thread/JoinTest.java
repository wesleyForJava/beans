package com.wesley.bean.thread;

public class JoinTest {
	public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadOne over!");
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadTwo over!");
            }
        });

        //启动线程
        threadOne.start();
        threadTwo.start();

        System.out.println("wait all child thread over!");

        //等待子线程执行完毕,返回
        threadOne.join();
        threadTwo.join();

        System.out.println("all child thread over!");

    }
}
