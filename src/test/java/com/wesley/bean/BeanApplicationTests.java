package com.wesley.bean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.apache.commons.io.IOUtils;
import org.junit.Before;

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
}
