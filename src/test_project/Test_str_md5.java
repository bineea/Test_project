package test_project;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class Test_str_md5 {

	public static void main(String[] args) {
		Test_str_md5 test = new Test_str_md5();
		test.jdk_md5();
		test.apache_md5();
	}
	
	
	private void jdk_md5() {
		String pwd = "05fafc701d5d4c2982c79948dac543ebapp_idB153E00C18650205CC4BA9438C6DD5A7param_json{\"trackNo\": \"JDLD10401870076\"}timestamp1631797230110v1.005fafc701d5d4c2982c79948dac543eb";
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");// ����һ��MD5���ܼ���ժҪ
	        md.update(pwd.getBytes());// ����md5����
	        /**
	         * digest()���ȷ������md5 hashֵ������ֵΪ8λ�ַ�����
	         * ��Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
	         * BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
	         * һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η���
	         */
	        String hashedPwd = new BigInteger(1, md.digest()).toString(16);// 16�Ǳ�ʾת��Ϊ16������
	        System.out.println(hashedPwd); 
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	}
	
	private void apache_md5() {
		String md5Hex = DigestUtils.md5Hex("05fafc701d5d4c2982c79948dac543ebapp_idB153E00C18650205CC4BA9438C6DD5A7param_json{\"trackNo\": \"JDLD10540201912\"}timestamp1631797230110v1.005fafc701d5d4c2982c79948dac543eb");
		System.out.println(md5Hex);
		System.out.println(md5Hex.equalsIgnoreCase("454F77116F6F5C4B9E5BC0AC002FDB2A"));
		
		
//		String md5Hex = DigestUtils.md5Hex("05fafc701d5d4c2982c79948dac543ebapp_idB153E00C18650205CC4BA9438C6DD5A7param_json{\"trackNo\":\"JDV000464360526\"}timestamp1631675398957v1.005fafc701d5d4c2982c79948dac543eb");
//		System.out.println(md5Hex);
		
	}
}
