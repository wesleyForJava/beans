package com.wesley.bean.bean;

public class MainTest {
	
	public static volatile int num=0;
	
	public static void addNum() {
		num++;
	}
	
	
   public static void main(String[] args) {
	   
      new Thread(()->{
    	  addNum();
    	  System.out.println();
      });

}
   
}