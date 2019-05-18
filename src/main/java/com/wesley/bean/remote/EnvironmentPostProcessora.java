//package com.wesley.bean.remote;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.Properties;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.env.EnvironmentPostProcessor;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.PropertiesPropertySource;
//import org.springframework.stereotype.Component;
//public class EnvironmentPostProcessora implements EnvironmentPostProcessor{
//
//	@Override
//	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
//		try (InputStream inputStream= new FileInputStream("D:/tmp/appliaction.properties")){
//			Properties source=new Properties();
//			source.load(inputStream);
//			PropertiesPropertySource propertiesPropertySource=new PropertiesPropertySource("my", source);
//			environment.getPropertySources().addLast(propertiesPropertySource);
//			System.out.println("====加载外部配置文件完毕====");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
