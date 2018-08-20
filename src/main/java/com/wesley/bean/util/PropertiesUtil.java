package com.wesley.bean.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUtil {
	private final ResourceBundle resourceBundle;
	
	private final String fileName;

	/**
	 * 构造函数实例化部分对象，获取文件资源对象
	 *
	 * @param fileName
	 */
	public PropertiesUtil(String fileName) {
		this.fileName = fileName;
		Locale locale=new Locale("zh","CN");
		this.resourceBundle = ResourceBundle.getBundle(this.fileName, locale);
	}
	
    /**
     * 根据传入的key获取对象的值 2016年12月17日 上午10:19:55 getValue
     *
     * @param key properties文件对应的key
     * @return String 解析后的对应key的值
     */
	public String getKey(String key) {
		String message = this.resourceBundle.getString(key);
		return message;
	}

	/**
     * 获取properties文件内的所有key值<br>
     * 2016年12月17日 上午10:21:20 getKeys
     *
     * @return
     */
	public Enumeration<String> getKeys() {
		return this.resourceBundle.getKeys();
	}
	
	
	
}
