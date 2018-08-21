package com.wesley.bean.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
@Component
public class InitBeanAndDestroyBean implements InitializingBean,DisposableBean{

	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行Inital方法");
	}
	
	
	@Override
	public void destroy() throws Exception {
		System.out.println("执行destroy()方法");
	}


	public void initMethod() {
		System.out.println("XML配置-执行InitBeanAndDestroyBeanTest：init-method方法");
	}
 
	public void destroyMethod() {
		System.out.println("XML配置-执行InitBeanAndDestroyBeanTest：destroy-method方法");
	}




}
