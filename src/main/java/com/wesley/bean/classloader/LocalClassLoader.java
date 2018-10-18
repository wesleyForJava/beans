package com.wesley.bean.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;


/**
 * @author Wesley
 *
 */
public class LocalClassLoader extends ClassLoader {

	//\com\wesley\bean\classloader
	
	 
	private String path ="E:/GoogleDownLoad/beans-master/target/classes";
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		   Class<?> cls = findLoadedClass(name);
		   
		   if(cls!=null) {
			   return cls;
		   }
		  if(name.endsWith(".DefintionClassLoader")) {
			  return super.loadClass(name);
		  }
		  try(
				  InputStream is=new FileInputStream(path+name.replace("/", ".")+".class");
				  ){
			           byte[] bytes = IOUtils.toByteArray(is);
			           return defineClass(name,bytes, 0, bytes.length);
		  } catch (FileNotFoundException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		} 
		return super.loadClass(name);
	}
	
	
}
