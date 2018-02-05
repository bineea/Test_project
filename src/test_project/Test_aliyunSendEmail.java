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
    private static final int ALIDM_SMTP_PORT = 25;//��80
    public static void main(String[] args) throws IOException {
        // ���÷����ʼ��Ļ�������
        final Properties props = new Properties();
        // ��ʾSMTP�����ʼ�����Ҫ���������֤
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // ���ʹ��ssl����ȥ��ʹ��25�˿ڵ����ã�������������,
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.port", "465");
        // �����˵��˺�
        props.put("mail.user", "service@testmail.txffp.com");
        // ����SMTP����ʱ��Ҫ�ṩ������
        props.put("mail.password", "TXffp20172018");
        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // �û���������
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
        // �����ʼ���Ϣ
        MimeMessage message = new MimeMessage(mailSession);
        try {
        // ���÷�����
        InternetAddress from = new InternetAddress("service@testmail.txffp.com");
        message.setFrom(from);
        Address[] a = new Address[1];
        a[0] = new InternetAddress("service@testmail.txffp.com");
        message.setReplyTo(a);
        // �����ռ���
        InternetAddress to = new InternetAddress("981344903@qq.com");
        message.setRecipient(MimeMessage.RecipientType.TO, to);
        // �����ʼ�����
        message.setSubject("�����ʼ�");
        // �����ʼ���������
//        message.setContent("���Ե�HTML�ʼ�", "text/html;charset=UTF-8");
        // ���ø���
        Multipart multipart = new MimeMultipart();
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent("���Ե�HTML�ʼ�", "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
         
        // ��Ӹ���������
        File pdf = new File("C:\\Users\\Administrator\\Desktop\\red.pdf");
        BodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(pdf);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
         
        // ���������Ľ���ļ�������ķ�������ʵ��MimeUtility.encodeWord�Ϳ��Ժܷ���ĸ㶨
        // �������Ҫ��ͨ�������Base64�����ת�����Ա�֤������ĸ����������ڷ���ʱ����������
        //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
        //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");
         
        //MimeUtility.encodeWord���Ա����ļ�������
        attachmentBodyPart.setFileName(MimeUtility.encodeWord(pdf.getName()));
        multipart.addBodyPart(attachmentBodyPart);
         
        // ��multipart����ŵ�message��
        message.setContent(multipart);
        message.saveChanges();
        // �����ʼ�
        Transport.send(message);
        }
        catch (MessagingException e) {
            String err = e.getMessage();
            // �����ﴦ��message���ݣ� ��ʽ�ǹ̶���
            System.out.println(err);
        }
    }

}
