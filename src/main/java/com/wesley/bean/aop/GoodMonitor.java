package com.wesley.bean.aop;

import java.util.Date;

public class GoodMonitor {
	
	public void start() {
		System.out.println("start time is "+new Date());
	}
	public void end() {
		System.out.println("end time is "+new Date());
	}

}
