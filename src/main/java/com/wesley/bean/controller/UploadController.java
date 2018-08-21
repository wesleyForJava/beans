package com.wesley.bean.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
    /**
     * 初始化上传文件界面，跳转到upload.html
     * @return
     */
    @RequestMapping(value = "/uploadindex",method = RequestMethod.GET )
    public String index(){
        return "upload";
    }
    
    
    /**
     * 提取上传方法为公共方法
     * @param uploadDir 上传文件目录
     * @param file 上传对象
     * @throws Exception
     */
    private void excuteUpload(String uploadDir,MultipartFile file) throws Exception{
    	//文件的后缀名
    	String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    	//上传的文件名
    	String fileName=UUID.randomUUID()+suffix;
    	//服务器端保存的文件对象
    	File serverFile=new File(uploadDir+fileName);
    	//将上传的文件写入服务器文件内
    	file.transferTo(serverFile);
    }
    
    /**
     * 上传文件方法
     * @param file 前台上传的文件对象
     * @return
     */
    @PostMapping(value="/upload" )
    @ResponseBody
    public String upload(HttpServletRequest request,MultipartFile file) {
    	try {
    	//上传目录的地址
    	String uploadDir=request.getSession().getServletContext().getRealPath("/")+"upload/";
    	//如果目录不存在创建目录
    	File fileDir=new File(uploadDir);
    	if(!fileDir.exists()) {
    		fileDir.mkdir();
    	}
    	//调用上传方法
			excuteUpload(uploadDir, file);
		} catch (Exception e) {
			e.printStackTrace();
			return "上传文件失败";
		}
		return "上传成功！";
    }
    
    /**
     * 上传多个文件
     * @param request 请求对象
     * @param file 上传文件集合
     * @return
     */
    @PostMapping(value="/uploads" )
    @ResponseBody
    public String uploads(HttpServletRequest request,MultipartFile [] files) {
    	try {
          //上传目录地址
    	String uploadDir=request.getServletContext().getRealPath("/")+"upload/";
    	File uploadDirsFile=new File(uploadDir);
    	if(!uploadDirsFile.exists()) {
    		uploadDirsFile.mkdir();
    	}
         for (int i = 0; i < files.length; i++) {
        	   if(files[i].getSize()!=0) {
				excuteUpload(uploadDir, files[i]);
		     }
         }
    	} catch (Exception e) {
    		e.printStackTrace();
    		return "上传失败";
    	}
    	return "上传成功";
    }
	
}
