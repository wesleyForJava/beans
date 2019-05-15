package com.wesley.bean.bean;


import org.springframework.beans.BeanUtils;

import com.wesley.bean.pojo.ExceptionInfoEntity;


public class MainTest {
   public static void main(String[] args) {
	   ExceptionInfoEntity ex=new ExceptionInfoEntity();
	   ex.setCode("1");
	   ex.setId(1111);
	   ex.setMessage("2");
	   ExceptionInfoEntity ex2=new ExceptionInfoEntity();
	   ex2.setCode("2");
	   ex2.setMessage("3");
	   BeanUtils.copyProperties(ex2, ex, "id");
					System.out.println(ex);
					System.out.println(ex2);
		
     }
}