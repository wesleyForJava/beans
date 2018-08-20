package com.wesley.bean.handler;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.InvocationHandler;

import com.wesley.bean.aop.PerformanceMonitor;
import com.wesley.bean.biz.IBiz;

public class BizInvcationHandler implements InvocationHandler{

	private IBiz target;
	private PerformanceMonitor monitor ;
	
	public BizInvcationHandler(IBiz target) {
		super();
		this.target = target;
		this.monitor = new PerformanceMonitor();
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		monitor.start();
		method.invoke(target);
		monitor.end();
		return null;
	}

	
	
	


}
