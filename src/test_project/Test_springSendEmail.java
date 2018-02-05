package test_project;

import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Base64Utils;

/**
 * @author Administrator
 */
public class Test_springSendEmail {
	public static void main(String[] args) throws Exception
	{
		Test_springSendEmail ts = new Test_springSendEmail();
		long allStart = System.currentTimeMillis();
		System.out.println("开始时间为："+allStart);
		for(int count=0;count<100;count++)
		{
			long start = System.currentTimeMillis();
			System.out.println("邮件发送前----当前时间为"+start);
			ts.send163Email();
			Thread.sleep(200);
			System.out.println("当前执行到："+count);
			long end = System.currentTimeMillis();
			System.out.println("邮件发送后----当前时间为"+end+", 共耗时："+(end - start));
		}
		long allend = System.currentTimeMillis();
		System.out.println("结束时间为："+allend+", 共耗时："+(allend - allStart));
	}
	
	public void send163Email()
	{
		try{
		   System.out.println("~~~~~~~~~~~~~~~~~~~~~~~SEND EMAIL START~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		   JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   senderImpl.setHost("smtpdm.aliyun.com");
		   senderImpl.setPort(25);
		   senderImpl.setProtocol("smtp");
		   senderImpl.setUsername("service@testmail.txffp.com");
		   senderImpl.setPassword("TXffp20172018");
		   senderImpl.setJavaMailProperties(props);
		   MimeMessage mimeMessge = senderImpl.createMimeMessage();
		   MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessge,true);
		   mimeMessageHelper.setFrom("service@testmail.txffp.com", "通行费发票");
		   mimeMessageHelper.setTo(new String[] {"995622825@qq.com"});
		   mimeMessageHelper.setSubject("添加附件");
		   mimeMessageHelper.setText("<html><head></head><body><h1>nice to meet you!</h1></body></html>",true); 
//		   mimeMessageHelper.setText("hello！测试邮件",false);
//		   File img = new File("红发.png");//文件名需要base64
//		   String filename =  "=?UTF-8?B?" + Base64Utils.encodeToString("红发.png".getBytes("UTF-8")) + "?=";
//		   mimeMessageHelper.addAttachment(filename,img);
//		   String filename = "=?UTF-8?B?" + Base64Utils.encodeToString("d77cf7075c83438494e168c6836ca666_OrderSheet.xlsx".getBytes("UTF-8")) + "?=";
//		   File xls = new File("C:\\Users\\Administrator\\Desktop\\test\\d77cf7075c83438494e168c6836ca666_OrderSheet.xlsx");
		   String filename = "=?UTF-8?B?" + Base64Utils.encodeToString("red.pdf".getBytes("UTF-8")) + "?=";
		   File xls = new File("C:\\Users\\Administrator\\Desktop\\red.pdf");
		   mimeMessageHelper.addAttachment(filename,xls);
		   senderImpl.send(mimeMessge);
		   System.out.println("~~~~~~~~~~~~~~~~~~~~~~~SEND EMAIL END~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		catch(Exception e)
		{
		   e.printStackTrace();
		}
	}
	
}
