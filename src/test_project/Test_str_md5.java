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
	        MessageDigest md = MessageDigest.getInstance("MD5");// 生成一个MD5加密计算摘要
	        md.update(pwd.getBytes());// 计算md5函数
	        /**
	         * digest()最后确定返回md5 hash值，返回值为8位字符串。
	         * 因为md5 hash值是16位的hex值，实际上就是8位的字符
	         * BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	         * 一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
	         */
	        String hashedPwd = new BigInteger(1, md.digest()).toString(16);// 16是表示转换为16进制数
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
