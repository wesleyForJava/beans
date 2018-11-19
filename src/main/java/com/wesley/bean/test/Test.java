package com.wesley.bean.test;

import com.wesley.bean.ProxyBuilder;
import com.wesley.bean.biz.IBiz;
import com.wesley.bean.biz.impl.IBizImpl;
import com.wesley.bean.handler.BizInvcationHandler;


public class Test {
	
	public static void main(String[] args) {
				 IBiz biz = new IBizImpl();
		 BizInvcationHandler hander = new BizInvcationHandler(biz);

	        IBiz proxy = (IBiz)ProxyBuilder.newProxyBuilder()
	            .setClassLoader(Thread.currentThread().getContextClassLoader())
	            .setInterFaces(IBiz.class)
	            .setInvocationHandler(hander)
	            .build();
	        proxy.dosomething();
	        
/*	        String sometime= "众怒,梦到,生活,美";
	        String[] split1 = StringUtils.split(sometime, ",");
	        for (int i = 0; i <split1.length; i++) {
				System.out.print(split1[i]+" ");
			}
	        String[] split = sometime.split(",");
	        for (int i = 0; i < split.length; i++) {
				System.out.print(split[i]+" ");
			}*/
	        
	        
/*	        ProxyBuilder.newProxyBuilder()
            .setClassLoader(Thread.currentThread().getContextClassLoader())
            .setInterFaces(new Class[] {IBiz.class})
            .setInvocationHandler(hander)
            .buildClassFile("proxy", ".");*/
	        
	    	String path="https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";
             String format = String.format(path, "a","b");
             System.out.println(format);
	}

}
