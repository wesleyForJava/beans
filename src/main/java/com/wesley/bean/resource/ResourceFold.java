package com.wesley.bean.resource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;

public class ResourceFold {
	
	public static void main(String[] args) throws IOException {
/*		String filepath ="c:/upload\\ebs\\professional\\0\\1d0d04fd-8c32-4fd4-808d-de13c93e6724.png";
		filepath.replace("\\", "/");
	    File file=new File(filepath);
		System.out.println(file.exists());*/
		
		@SuppressWarnings("unused")
		String filePath="D:/ebs/img/contBackground1.jpg";
		String fileAbsolutePathString="C:/upload/ebs/professional/0/9de56181-8723-4ee8-9a93-dd32dab0300c.jpg";
		WritableResource writableResource = new PathResource(fileAbsolutePathString);
		
		System.out.println(writableResource.getFilename());
		System.out.println(writableResource.getURI());
		System.out.println(writableResource.getURL());
		System.out.println(writableResource.contentLength());
		System.out.println(writableResource.getDescription());
		System.out.println(writableResource.getClass());
		
		getPath();
		
/*		InputStream inputStream = writableResource.getInputStream();
		DataInputStream dataInputStream=new DataInputStream(new FileInputStream(filePath));
		byte [] aa=new byte[1024];
		int i;
		while((i=inputStream.read())!=-1) {
			System.out.println(i);
		}
		inputStream.close();*/
	}
		//imgFile是图片的路径
/*		String imgFile="D:/ebs/img/contBackground1.jpg";
		getImageStr(imgFile);
	}*/
/*	public static void getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;    
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}    // 加密
		//BASE64Encoder  encoder = new BASE64Encoder();    
		System.out.println(Base64Coder.encode(data));
	}*/
	
	
	public static void getPath(){
        try {
            String  filePath = "D:/Working/Spring/src/main/resources/conf/resourceFileTest.txt";
            //使用系统文件路径方式加载文件
            WritableResource res1 = new PathResource(filePath);
            //使用类路径方式加载文件
            Resource res2 = new ClassPathResource("conf/resourceFileTest.txt");
 
            EncodedResource encRes = new EncodedResource(res2,"UTF-8");//可以对资源进行特定编码
            @SuppressWarnings("unused")
			File file1 = res1.getFile();//获得File对象
            @SuppressWarnings("unused")
			File file2 = encRes.getResource().getFile();//获得File对象
 
            OutputStream stream1 = res1.getOutputStream();//获取文件输出流
            stream1.write("我是小王瑞".getBytes());
            stream1.close();
 
            //使用Resource接口读资源文件
            InputStream ins1 = res1.getInputStream();//获取文件输入流
            @SuppressWarnings("unused")
			InputStream ins2 = res2.getInputStream();
 
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while ((i = ins1.read())!=-1){
                baos.write(i);
            }
            System.out.println(baos.toString());
 
            System.out.println("res1:" + res1.getFilename());//获得文件名
            System.out.println("res2:" + res2.getFilename());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
