package com.wesley.bean.buidler;

import org.springframework.cglib.proxy.Proxy;

import com.wesley.bean.handler.BicInvocationHandler;

public class ProxyBuilderOne {
	
	private Class<?> [] interfaces;
	private BicInvocationHandler handler;
	private ClassLoader classLoader=ProxyBuilderOne.class.getClassLoader();
	
	
	public ProxyBuilderOne setInterfaces(Class<?> ... interfaces) {
		this.interfaces = interfaces;
		return this;
	}
	public ProxyBuilderOne setHandler(BicInvocationHandler handler) {
		this.handler = handler;
		return this;
	}
	public ProxyBuilderOne setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		return this;
	}
	
	
	public Object build() {
		return Proxy.newProxyInstance(classLoader, interfaces, handler);
	}
}
