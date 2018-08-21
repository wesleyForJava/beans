package com.wesley.bean.util;

import java.awt.Color;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import com.wesley.bean.poi.ExcelData;

public class ExcelUtils {
	public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
        exportExcel(data, response.getOutputStream());
    }

	private static void exportExcel(ExcelData data, ServletOutputStream outputStream) throws IOException {
		XSSFWorkbook wb=new XSSFWorkbook();
		try {
			String sheetName = data.getName();
			if(null != sheetName) {
				sheetName="sheet1";
			}
			XSSFSheet sheet = wb.createSheet(sheetName);
			 writeExcel(wb, sheet, data);
             wb.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			outputStream.close();
		}
	}

	private static void writeExcel(XSSFWorkbook wb, XSSFSheet sheet, ExcelData data) {
		int rowIndex=0;
		rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
		writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
		autoSizeColumns(sheet, data.getTitles().size() + 1);
	}



	private static int writeTitlesToExcel(XSSFWorkbook wb, XSSFSheet sheet, List<String> titles) {
		 int rowIndex = 0;
         int colIndex = 0;
        try {
			XSSFFont titleFont = wb.createFont();//创建字体
			titleFont.setFontName("simsun");//设置（simsun）字体  设置字体名称
			titleFont.setColor(IndexedColors.BLACK.index);//设置字体颜色
//			titleFont.setUnderline(FontFormatting.U_SINGLE);//设置下划线
//			titleFont.setTypeOffset(FontFormatting.SS_SUPER);//设置上标下标
//			titleFont.setStrikeout(true);//设置删除线

	        XSSFCellStyle titleStyle = wb.createCellStyle();//创建边框
	        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); //水平居中
	        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
	        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));//设置图案颜色
	        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);//设置图案样式
	        titleStyle.setFont(titleFont);//设置字体
	        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
	        Row titleRow = sheet.createRow(rowIndex);
	        colIndex = 0;
	        for (String field : titles) {
	           Cell cell = titleRow.createCell(colIndex);
	            cell.setCellValue(field);
	            cell.setCellStyle(titleStyle);
	            colIndex++;
	        }
	        rowIndex++;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return rowIndex;
	}
	private static int writeRowsToExcel(XSSFWorkbook wb, XSSFSheet sheet, List<List<Object>> rows, int rowIndex) {
		int colIndex = 0;
		Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);
 
        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
		
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            // dataRow.setHeightInPoints(25);
            colIndex = 0;
 
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
 
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;

	}

	private static void autoSizeColumns(XSSFSheet sheet, int columnNumber) {
		  for (int i = 0; i < columnNumber; i++) {
	            int orgWidth = sheet.getColumnWidth(i);
	            sheet.autoSizeColumn(i, true);
	            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
	            if (newWidth > orgWidth) {
	                sheet.setColumnWidth(i, newWidth);
	            } else {
	                sheet.setColumnWidth(i, orgWidth);
	            }
	        }

	}
	private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
		    style.setBorderTop(border);//上边框
	        style.setBorderLeft(border);//左边框
	        style.setBorderRight(border);//右边框
	        style.setBorderBottom(border);//下边框
	        style.setBorderColor(BorderSide.TOP, color);//上边框颜色
	        style.setBorderColor(BorderSide.LEFT, color);//左边框颜色
	        style.setBorderColor(BorderSide.RIGHT, color);//右边框颜色
	        style.setBorderColor(BorderSide.BOTTOM, color);//下边框颜色
	}

}
