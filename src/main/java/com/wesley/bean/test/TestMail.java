package com.wesley.bean.test;

import java.util.ArrayList;
import com.wesley.bean.emun.MailContentTypeEnum;
import com.wesley.bean.pojo.core.MailSender;

public class TestMail {
   public static void main(String[] args) {
       try {
		new MailSender()
		   .titile("测试SpringBoot发送邮件")
		   .content("用SMTPMessage测试发送")
		   .contentType(MailContentTypeEnum.TEXT)
		   .address(new ArrayList<String>(){
			private static final long serialVersionUID = 1L;
		{      add("834480214@qq.com");
		   }})
		   .send();
	} catch (Exception e) {
		e.printStackTrace();
	}
/*	  Locale locale=new Locale("zh", "CN");
	   ResourceBundle rb = ResourceBundle.getBundle("mail_zh_CN",locale);
	   System.out.println(rb.getString("mail.from.nickname"));*/
}
}
