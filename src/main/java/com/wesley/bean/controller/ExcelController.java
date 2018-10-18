package com.wesley.bean.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.poi.ExcelData;
import com.wesley.bean.util.ExcelUtils;

@RestController
@RequestMapping("/excel")
public class ExcelController {
	    @GetMapping(value = "/export")
	    public void excel(HttpServletResponse response) throws Exception {
		 ExcelData data = new ExcelData();
	        data.setName("hello");
	        List<String> titles = new ArrayList<String>();
	        titles.add("a1");
	        titles.add("a2");
	        titles.add("a3");
	        data.setTitles(titles);

	        List<List<Object>> rows = new ArrayList<List<Object>>();
	        List<Object> row = new ArrayList<Object>();
	        row.add("11111111111");
	        row.add("22222222222");
	        row.add("3333333333");
	        rows.add(row);
	        List<Object> row1 = new ArrayList<Object>();
	        row1.add("3333333333333");
	        row1.add("2222222222222");
	        row1.add("1111111111111");
	        rows.add(row1);

	        data.setRows(rows);


	        //生成本地
/*	        File f = new File("c:/test.xlsx");
	        FileOutputStream out = new FileOutputStream(f);
	        ExcelUtils.exportExcel(response,f.getName(),data);
	        out.close();*/
	        SimpleDateFormat fdate=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	        String fileName=fdate.format(new Date())+".xlsx";
	        ExcelUtils.exportExcel(response,fileName,data);
	 }
}
