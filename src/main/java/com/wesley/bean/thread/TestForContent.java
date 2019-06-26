package com.wesley.bean.thread;

public class TestForContent { //1
	static final int  LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        long[][] array = new long[LINE_NUM][COLUM_NUM];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < LINE_NUM ; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i*2+j;
            }
        }

        long endTime = System.currentTimeMillis();

        long cacheTime = endTime - startTime;

        System.out.println("cache time:" + cacheTime + "ms");
    }
    @sun.misc.Contended
    public final static class FilledLong{
    	
    	public volatile long value=0L;
    	public long p1,p2,p3,p4,p5;
    }
}
