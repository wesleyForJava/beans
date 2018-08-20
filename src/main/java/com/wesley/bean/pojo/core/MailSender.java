package com.wesley.bean.pojo.core;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.sun.mail.smtp.SMTPMessage;
import com.wesley.bean.emun.MailContentTypeEnum;
import com.wesley.bean.pojo.MailEntity;
import com.wesley.bean.util.PropertiesUtil;

public class MailSender {
	/**
	 * 邮件实体
	 */
	private MailEntity mail=new MailEntity();
  
	/**
     * 设置邮件标题
     * @param title
     * @return
     */
	public MailSender titile(String title) {
		mail.setTitle(title);
		return this;
	}

    /**
     * 设置邮件内容
     * @param content
     * @return
     */
	public MailSender content(String content) {
		mail.setContent(content);
		return this;
	}
	/**
	 * 设置邮件格式
	 * @param contentType
	 * @return
	 */
	public MailSender contentType(MailContentTypeEnum typeEnum) {
		mail.setContentType(typeEnum.getValue());
		return this;
	}
	
	/**
	 * 设置请求目标邮件地址
	 */
	public MailSender address(List<String> list) {
		mail.setList(list);
		return this;
		
	}
	
	
	/**
	 * 执行发送邮件
	 * 
	 */
	public void send() throws Exception{
		//默认使用html发送内容
		if(mail.getContentType()==null) {
			mail.setContentType(MailContentTypeEnum.HTML.getValue());
		}
		
		
		if(mail.getTitle()==null || mail.getTitle().trim().length()==0) {
			throw new Exception("邮件主题没有设置，请调用title方法进行设置");
		}
		
		if(mail.getContent()==null || mail.getContent().trim().length()==0) {
			throw new Exception("邮件内容没有设置，请调用content方法进行设置");
		}
		if(mail.getList().size()==0) {
			throw new Exception("没有接受者邮箱地址.调用address方法设置");
			
		}
		
		 //读取/resource/mail_zh_CN.properties文件内容
		PropertiesUtil properties=new PropertiesUtil("mail_zh_CN");
		
		// 创建Properties 类用于记录邮箱的一些属性
		Properties props=new Properties();
		
		//表示SMTP发送必须进行身份认证
		props.put("mail.smtp.auth", "true");
		
		//此处填写smtp的服务器
		props.put("mail.smtp.host", properties.getKey("mail.smtp.service"));
		
		//设置端口号，QQ邮箱给出了两个端口465/587
		props.put("mail.smtp.port", properties.getKey("mail.smtp.prot"));
		
		//设置发送邮箱
		props.put("mail.user", properties.getKey("mail.from.address"));
		
		//设置发送邮箱的16位smtp口令
		props.put("mail.password", properties.getKey("mail.from.smtp.pwd"));
		
		
		//构建授权信息，用于smtp的身份验证
        Authenticator authenticator=new Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        	         String userName = props.getProperty("mail.user");
        	         String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
        	}
		};
		 // 使用环境属性和授权信息，创建邮件会话
		Session session=Session.getInstance(props, authenticator);
		//创建邮件消息
		//MimeMessage message=new MimeMessage(session);
		SMTPMessage message=new SMTPMessage(session);
		
		//设置发件人
		String nickName = MimeUtility.encodeText(properties.getKey("mail.from.nickname")); //MimeUtility 避免附件乱码的工具类
		InternetAddress address=new InternetAddress(nickName+"<"+props.getProperty("mail.user")+">");
		message.setFrom(address);
		
		//设置邮件标题
		message.setSubject(mail.getTitle());
		
		//html发送邮件
		if (mail.getContentType().equals(MailContentTypeEnum.HTML.getValue())) {
			message.setContent(mail.getContent(),mail.getContentType());
		}else if(mail.getContentType().equals(MailContentTypeEnum.TEXT.getValue())) {
			message.setText(mail.getContent());
		}
		//发送邮箱地址
		List<String> addresslList=mail.getList();
		for (int i = 0; i < addresslList.size(); i++) {
			try {
				//设置收件人邮箱
				InternetAddress address2= new InternetAddress(addresslList.get(i));
				message.addRecipient(RecipientType.TO, address2);
				//发送邮件
				Transport.send(message);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
