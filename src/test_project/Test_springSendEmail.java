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
		ts.send163Email();
	}
	
	public void send163Email()
	{
		try{
		   System.out.println("~~~~~~~~~~~~~~~~~~~~~~~SEND EMAIL START~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		   JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   senderImpl.setHost("smtp.163.com");
		   senderImpl.setPort(25);
		   senderImpl.setProtocol("smtp");
		   senderImpl.setUsername("guo__wenbin@163.com");
		   senderImpl.setPassword("wy031166132962");
		   senderImpl.setJavaMailProperties(props);
		   MimeMessage mimeMessge = senderImpl.createMimeMessage();
		   MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessge,true);
		   mimeMessageHelper.setFrom("guo__wenbin@163.com", "BINEEA");
		   mimeMessageHelper.setTo(new String[] {"995622825@qq.com"});
		   mimeMessageHelper.setSubject("添加附件");
		   mimeMessageHelper.setText("<html><head></head><body><h1>nice to meet you!</h1></body></html>",true); 
//		   mimeMessageHelper.setText("hello！测试邮件",false);
//		   File img = new File("红发.png");//文件名需要base64
//		   String filename =  "=?UTF-8?B?" + Base64Utils.encodeToString("红发.png".getBytes("UTF-8")) + "?=";
//		   mimeMessageHelper.addAttachment(filename,img);
		   String filename = "=?UTF-8?B?" + Base64Utils.encodeToString("d77cf7075c83438494e168c6836ca666_OrderSheet.xlsx".getBytes("UTF-8")) + "?=";
		   File xls = new File("C:\\Users\\Administrator\\Desktop\\test\\d77cf7075c83438494e168c6836ca666_OrderSheet.xlsx");
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
