package com.wesley.bean.mulitthreading;

public class SellTicektesDemo {
    public static void main(String[] args) {
        // 创建SellTicektes对象
        SellTicektes st = new SellTicektes() ;
        // 创建Thread对象
        Thread t1 = new Thread(st , "窗口1") ;
        Thread t2 = new Thread(st , "窗口2") ;
        Thread t3 = new Thread(st , "窗口3") ;
        // 启动线程
        t1.start() ;
        t2.start() ;
        t3.start() ;
    }
}


