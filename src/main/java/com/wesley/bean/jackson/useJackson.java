package com.wesley.bean.jackson;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class useJackson {
		  @SuppressWarnings("unchecked")
		public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
			//Map套List的Json串
			// String src = "{"list1":[{"key1":"var1","key2":"var2"},{"key3":"var3","key4":"var4"}],"list2":[{"key5":"var5","key6":"var6"},{"key7":"var7","key8":"var8"}]}";
			//Map套Map的Json串
			   String src2="{\"map1\":{\"key1\":\"val1\",\"key2\":\"val2\"},\"map2\":{\"key3\":\"val3\",\"key4\":\"val4\"}}";
			   
			   
	/*		    Map<String,Object> map=mapper.readValue(src,Map.class);
		        for(Map.Entry<String, Object> entry : map.entrySet()){
		            System.out.println(entry.getKey()+":"+entry.getValue());
		            System.out.println(entry.getValue().getClass());
		        }*/
			   ObjectMapper mapper=new ObjectMapper();
		        System.out.println("------------------------------------------------------------");
		        Map<String,Object> map2=mapper.readValue(src2,Map.class);
		        for(Map.Entry<String, Object> entry : map2.entrySet()){
		            System.out.println(entry.getKey()+":"+entry.getValue());
		            System.out.println(entry.getValue().getClass());
		        }
		}
	}
