package com.wesley.bean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wesley.bean.classloader.LocalClassLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void Before() {
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(get("/user").param("name", "lily").contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());
	}
	
	@Test
	public void test() throws Exception {
		 InputStream is =null;
		try{
            byte[] bytes = new byte[4];
            IOUtils.toInputStream("hello world","utf-8");
            IOUtils.read(is, bytes);
            System.out.println(new String(bytes));

            bytes = new byte[10];
            is = IOUtils.toInputStream("hello world","UTF-8");
            IOUtils.read(is, bytes, 2, 4);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
          is.close();
		}
	}
	
	@Test
    public void readFullyTest() throws IOException{
        byte[] bytes = new byte[8];
        
        try(InputStream is  = IOUtils.toInputStream("hello world","UTF-8");
        		){
        	 IOUtils.readFully(is,bytes);
        	 System.out.println(new String(bytes));
        }
    }
	
	
	@Test
    public void readLinesTest() throws FileNotFoundException, IOException{
        try(InputStream is = new FileInputStream("D://doo.txt");){
        	  List<String> lines = IOUtils.readLines(is,"UTF-8");
              for(String line : lines){
                  System.out.println(line);
        }
             
        }
	}
	
	
	@Test
    public void writeLinesTest() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("hello");
        lines.add("list");
        lines.add("to");
        lines.add("file");
      try(OutputStream os = new FileOutputStream("E:/test.txt");){
        IOUtils.writeLines(lines,IOUtils.LINE_SEPARATOR,os,"Utf-8");
      }
    }
	
	@Test
	public void readClassLoader() throws ClassNotFoundException {
		LocalClassLoader lcl=new LocalClassLoader();
		Class<?> loadClass = lcl.loadClass("com.wesley.bean.classloader.DefintionClassLoader");
		Field field = FieldUtils.getField(loadClass, "keyString",true);
		Object value;
		try {
			value = field.get(loadClass.newInstance());
			System.out.println(value);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestURLClassLoader() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException, IOException {
		URLClassLoader ucl=new URLClassLoader(new URL[] {new URL("file:/E:/GoogleDownLoad/beans-master/target/classes")});
		Class<?> loadClass =ucl.loadClass("com.wesley.bean.classloader.DefintionClassLoader");
		Field field = FieldUtils.getField(loadClass, "keyString",true);
		Object value = field.get(loadClass.newInstance());
		System.out.println(value);
		ucl.close();
		
	}
	
	
/*	@Test
	public void TestClassLoader() {
		ClassLoader c = ClassLoader.getSystemClassLoader();
		System.out.println(c);
		while((c=c.getParent())!=null) {
			System.out.println(c);
		}
	}*/
	
	@Test
	public void TestIntegerCache() {
		Integer a=127;
		Integer b=127;
		
		Integer c=128;
		Integer d=128;
		Integer c1=new Integer(128);
		Integer d1=new Integer(128);
		
		System.out.println(a==b);
		System.out.println(c1==d1);
		System.out.println(c==d);
		
	}
	
}
