package com.wesley.bean.thread;

public class TestForContent2 {//2

    static final int  LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        long[][] array = new long[LINE_NUM][COLUM_NUM];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < COLUM_NUM ; i++) {
            for (int j = 0; j < LINE_NUM ; j++) {
                array[j][i] = i*2+j;
            }
        }

        long endTime = System.currentTimeMillis();

        long cacheTime = endTime - startTime;

        System.out.println("cache time :" + cacheTime + "ms");
    }
}