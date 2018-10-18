package com.wesley.bean.test;


import com.wesley.bean.biz.IBic;
import com.wesley.bean.biz.impl.IBicImpl;
import com.wesley.bean.buidler.ProxyBuilderOne;
import com.wesley.bean.handler.BicInvocationHandler;


public class TestOne {
	    private static IBic bic=new IBicImpl();     
	    private static  BicInvocationHandler handler=new BicInvocationHandler(bic);
	    
	   public static void main(String[] args) {
		   IBic ibic = (IBic) new ProxyBuilderOne()
		  .setClassLoader(new ProxyBuilderOne().getClass().getClassLoader())
		  .setHandler(handler)
		  .setInterfaces(IBic.class)
		  .build();
		   ibic.writesomemore();
	}
}
