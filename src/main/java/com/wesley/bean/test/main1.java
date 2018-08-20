package com.wesley.bean.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;


public class main1 extends WebMvcConfigurerAdapter{
	public static void main(String[] args) {
          
		
		
/*		ApplicationContext ap = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			Class<?> loadClass = classLoader.loadClass("com.wesley.bean.BeanApplication");
			
			try {
				Constructor<?> constructor = loadClass.getConstructor(new Class[] {});
				Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();
				Field[] declaredFields = loadClass.getDeclaredFields();
			    for (int i = 0; i < declaredAnnotations.length; i++) {
					System.out.println(declaredAnnotations[i]);
				} 
				
			    for (int i = 0; i < declaredFields.length; i++) {
			    	System.out.println(declaredFields[i]);
			    } 
			    
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	 
	    //判断是否为空
	     String input = "";
	     boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
	     System.out.println("input " + (isNullOrEmpty?"is":"is not") + " null or empty.");
	    
	    //补全字符串之后
        int minLength = 4;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);
        
        //补全字符串之前  
        String padStartResult = Strings.padStart("1", 2, '0');
        System.out.println("padStartResult is " + padStartResult);
		
        //截取相同的开头
		//Strings.commonPrefix(a,b) demo
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a,b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);
          
        //截取相同的结尾
        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c,d);
        System.out.println("c,d common suffix is " + ourSuffix);
		
        
        //"字符串的二次拆分" 可以看看是怎么拆分的
        String toSplitString = "a=b;c=d,e=f";
          Map<String,String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
          for (Map.Entry<String,String> entry : kvs.entrySet()) {
              System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
          }
          
          //合并字符串
          String joinResult = Joiner.on(" ").join(new String[]{"hello","world"});//空格代表间隔
               System.out.println(joinResult);
		
           //Splitter方法可以对字符串做二次的拆分，对应的Joiner也可以逆向操作，将Map<String,String>做合并。我们看下下面的例子：    
               Map<String,String> map = new HashMap<String,String>();
                     map.put("a", "b");
                     map.put("c", "d");
                     String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
                     System.out.println(mapJoinResult);  
                     
           //在开发中经常会需要比较两个对象是否相等，这时候我们需要考虑比较的两个对象是否为null，然后再调用equals方法来比较是否相等，
          //google guava库的com.google.common.base.Objects类提供了一个静态方法equals可以避免我们自己做是否为空的判断，
         //示例如下：  
                       Object a1 = null;
                       Object b1 = new Object();
                       boolean aEqualsB = Objects.equal(a1, b1);
                       System.out.println(aEqualsB);
/*               try {
				InputStream inputStream = new DefaultResourceLoader().getResource("classpath:/application.properties").getInputStream();
				int i;
				while((i=inputStream.read())!=-1) {
			       System.out.println(i);
				}
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
/*		   Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
	                .trimResults()
	                .omitEmptyStrings()
	                .split("hello,word,,世界，水平");

	        for (String item : splitResults) {
	            System.out.println(item);
	        }*/	
	}

	private static void AbstractApplicationContext() {
	}
}
