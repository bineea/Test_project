package test_project;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailConstants;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Base64Utils;

import cn.com.taiji.common.manager.pub.SendMailHelper;
import cn.com.taiji.common.model.pub.SmtpMailModel;
import cn.com.taiji.common.model.pub.SmtpServerType;
import cn.com.taiji.common.pub.Base64Tools;

/**
 * @author Administrator
 */
public class Test_springSendEmail {
	public static void main(String[] args) throws Exception
	{
		Test_springSendEmail ts = new Test_springSendEmail();
		ts.sendTjEmail();
	}
	
	public void sendTjEmail() throws Exception
	{
		SmtpMailModel model = new SmtpMailModel();
		model.setFrom("txffp@all-in-data.com");
		model.setPersonal("通信费发票通知");
		model.setUser("txffp@all-in-data.com");
		model.setPass("xysj@2017");
		model.setTo(new String[] { "981344903@qq.com", "995622825@qq.com" });
		model.setMailServer("smtp.all-in-data.com").setPort(465).setServerType(SmtpServerType.SSL);

		model.setSubject("我就是邮箱");
		model.setText("注意查看附件，我不是垃圾邮件。。。");
		List<File> attachs = new ArrayList<File>();
		attachs.add(new File("d77cf7075c83438494e168c6836ca666_OrderSheet.xlsx"));
		attachs.add(new File("红发.png"));
		model.setAttachs(attachs);
		sendMail(model);
	}
	
	public static void sendMail(SmtpMailModel model) throws MessagingException, UnsupportedEncodingException
	{
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(model.getMailServer());
		sender.setPort(model.getPort());
		sender.setProtocol("smtp");
		sender.setUsername(model.getUser());
		sender.setPassword(model.getPass());
		sender.setJavaMailProperties(getJavaMailProperties(model));

		MimeMessage message = sender.createMimeMessage();
		fillMessage(message, model);
		sender.send(message);
	}

	private static void fillMessage(MimeMessage message, SmtpMailModel model)
			throws MessagingException, UnsupportedEncodingException
	{
		MimeMessageHelper helper = new MimeMessageHelper(message, true, model.getEncoding());
		helper.setSentDate(new Date());
		helper.setSubject(model.getSubject());
		helper.setFrom(model.getFrom(), model.getPersonal());
		helper.setTo(model.getTo());
		helper.setText(model.getText(), model.isHtml());
		for (File file : model.getAttachs())
		{
			if (!file.exists())
			{
				continue;
			}
			String fileName = file.getName();
//				fileName = "=?UTF-8?B?" + EncodeTool.encodeBase64UTF8(fileName) + "?=";
			fileName = "=?UTF-8?B?" + Base64Tools.encodeToString(fileName.getBytes("UTF-8")) + "?=";
			helper.addAttachment(fileName, file);
		}
	}

	private static Properties getJavaMailProperties(SmtpMailModel model)
	{
		Properties props = new Properties();
		// props.put("mail.debug", "true");
		props.put(EmailConstants.MAIL_SMTP_AUTH, model.isStmpAuth() ? true : false);
		switch (model.getServerType())
		{
		case SSL:
			props.put(EmailConstants.MAIL_SMTP_SSL_ENABLE, true);
			break;
		case STARTTLS:
			props.put(EmailConstants.MAIL_TRANSPORT_STARTTLS_ENABLE, true);
			break;
		default:
			break;
		}
		return props;
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
	
	public void sendXYEmail()
	{
		try{
		   System.out.println("~~~~~~~~~~~~~~~~~~~~~~~SEND EMAIL START~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		   JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   senderImpl.setHost("smtp.all-in-data.com");
		   senderImpl.setPort(465);
		   senderImpl.setProtocol("smtp");
		   senderImpl.setUsername("txffp@all-in-data.com");
		   senderImpl.setPassword("xysj@2017");
		   senderImpl.setJavaMailProperties(props);
		   MimeMessage mimeMessge = senderImpl.createMimeMessage();
		   MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessge,true);
		   mimeMessageHelper.setFrom("txffp@all-in-data.com", "BINEEA");
		   mimeMessageHelper.setTo(new String[] {"981344903@qq.com"});
		   mimeMessageHelper.setSubject("添加附件");
		   mimeMessageHelper.setText("<html><head></head><body><h1>nice to meet you!</h1></body></html>",true); 
//		   mimeMessageHelper.setText("hello！测试邮件",false);
//		   File img = new File("红发.png");//文件名需要base64
//		   String filename =  "=?UTF-8?B?" + "红发.png" + "?=";
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
