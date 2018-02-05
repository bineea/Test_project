package test_project;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Test_aliyunSendEmail {

//	private static final String ALIDM_SMTP_HOST = "service@testmail.txffp.com";
	private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
//    private static final String ALIDM_SMTP_HOST = "service@testmail.txffp.com";
    private static final int ALIDM_SMTP_PORT = 25;//或80
    public static void main(String[] args) throws IOException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.port", "465");
        // 发件人的账号
        props.put("mail.user", "service@testmail.txffp.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "TXffp20172018");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
        // 设置发件人
        InternetAddress from = new InternetAddress("service@testmail.txffp.com");
        message.setFrom(from);
        Address[] a = new Address[1];
        a[0] = new InternetAddress("service@testmail.txffp.com");
        message.setReplyTo(a);
        // 设置收件人
        InternetAddress to = new InternetAddress("981344903@qq.com");
        message.setRecipient(MimeMessage.RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject("测试邮件");
        // 设置邮件的内容体
//        message.setContent("测试的HTML邮件", "text/html;charset=UTF-8");
        // 设置附件
        Multipart multipart = new MimeMultipart();
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent("测试的HTML邮件", "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
         
        // 添加附件的内容
        File pdf = new File("C:\\Users\\Administrator\\Desktop\\red.pdf");
        BodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(pdf);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
         
        // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
        // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
        //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
        //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
         
        //MimeUtility.encodeWord可以避免文件名乱码
        attachmentBodyPart.setFileName(MimeUtility.encodeWord(pdf.getName()));
        multipart.addBodyPart(attachmentBodyPart);
         
        // 将multipart对象放到message中
        message.setContent(multipart);
        message.saveChanges();
        // 发送邮件
        Transport.send(message);
        }
        catch (MessagingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        }
    }

}
