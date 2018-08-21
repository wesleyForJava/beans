package com.wesley.bean.poi;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
@Data
public class ExcelData implements Serializable
{	private static final long serialVersionUID = 1L;
		//表头
		private List<String> titles;
		
		// 数据
		private List<List<Object>> rows;
		
		// 页签名称
		private String name;

}
