package com.wesley.bean.aop;

public class PerformanceMonitor {
	 public void start() {
	        System.out.println("开始时间: " + String.valueOf(System.currentTimeMillis()));
	    }

	    public void end() {
	        System.out.println("结束时间: " + String.valueOf(System.currentTimeMillis()));
	    }
}
