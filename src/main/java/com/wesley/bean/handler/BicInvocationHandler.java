package com.wesley.bean.handler;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.InvocationHandler;

import com.wesley.bean.aop.GoodMonitor;
import com.wesley.bean.biz.IBic;

public class BicInvocationHandler implements InvocationHandler{

	private IBic target;
	
	public BicInvocationHandler(IBic target) {
		super();
		this.target = target;
		this.goodMonitor = new GoodMonitor();
	}


	@Autowired
	private GoodMonitor goodMonitor;//
	
	
	@Override
	public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable {
		goodMonitor.start();
		paramMethod.invoke(target);
		goodMonitor.end();
		return null;
	}

}
