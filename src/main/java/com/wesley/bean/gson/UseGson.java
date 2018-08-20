package com.wesley.bean.gson;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.wesley.bean.pojo.User;

public class UseGson {
	public static void main(String[] args) {
		
	//解成对象
	//Fromat mFromat = new Gson().fromJson(jsonStringObject, Fromat.class);
	Gson gson = new Gson();
	String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
	String[] strings = gson.fromJson(jsonArray, String[].class);
	System.out.println(strings.length);
	System.out.println(strings[2]);
	
	   User bean = new User();
       bean.setEmail("24");
       bean.setUsername("扎巴也"); //方法是setName,属性是firstName

       String fastJson = JSON.toJSONString(bean);
       System.out.println("fastJson=" + fastJson);

       String gson1 = new Gson().toJson(bean);
       System.out.println("gson=" + gson1);
       //ThreadPoolExecutor
	}
}
