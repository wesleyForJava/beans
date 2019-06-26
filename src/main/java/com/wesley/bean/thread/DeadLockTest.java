package com.wesley.bean.thread;
public class DeadLockTest {

    public static Object resourceA = new Object();
    public static Object resourceB = new Object();

    public static void main(String[] args) {

        //创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println(Thread.currentThread() + "get resourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resourceB");
                    synchronized (resourceB){
                        System.out.println(Thread.currentThread() + "get resourceB");
                    }
                }
            }
        });

//      创建线程B
      Thread threadB = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (resourceA){
                System.out.println(Thread.currentThread() + "get ResourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resourceB");
                synchronized (resourceB){
                    System.out.println(Thread.currentThread() + "get resourceB");
                }
            }
      
        }
      });


        threadA.start();
        threadB.start();
    }

}

