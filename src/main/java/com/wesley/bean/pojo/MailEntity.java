package com.wesley.bean.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;



@Data
public class MailEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	//此处填写SMTP服务器
    private String smtpService;
    //设置端口号
    private String smtpPort;
    //设置发送邮箱
    private String fromMailAddress;
    // 设置发送邮箱的STMP口令
    private String fromMailStmpPwd;
    //设置邮件标题
    private String title;
    //设置邮件内容
    private String content;
    //内容格式（默认采用html）
    private String contentType;
    //接受邮件地址集合
    private List<String> list = new ArrayList<>();
}
