package com.wesley.bean.test;


import java.util.ArrayList;
import java.util.List;

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
		   
		   List<String> arrayList = new ArrayList<String>() {{
			    add("test");
			    add("test2");
			}};
			for (String string : arrayList) {
				System.out.println(string);
			}
	}
}
