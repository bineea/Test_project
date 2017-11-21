package test_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test_file_md5 {
	
	public static void main(String[] args)
	{
		String str1 = Test_file_md5.generateMD5(new File("new.txt"));
		String str2 = Test_file_md5.generateMD5(new File("test.txt"));
		System.out.println("str1:"+str1);
		System.out.println("str2:"+str2);
	}
	/**
	 * һ���Դ�����ļ��ֽ���
	 */
	private static final int ONE_HANDLE_FILE_BYTE_COUNT = 8192;

	/**
	 * MD5 �㷨
	 */
	private static final String MD5_ALGORITHM = "MD5";

	/**
	 * �����ݵ�����ֵ
	 */
	private static final int NOT_DATA_INDEX = -1;

	/**
	 * ��������
	 */
	private static final int POSITIVE_SIGN = 1;

	/**
	 * ʮ������
	 */
	private static final int HEXADECIMAL = 16;

	/**
	 * �����ļ����ݵ� MD5 ֵ
	 *
	 * @param file
	 * @return
	 */
	public static String generateMD5(File file) {
	    if (!file.isFile())//�����ļ�
	        return null;

	    MessageDigest digest;
	    FileInputStream in = null;
	    byte buffer[] = new byte[ONE_HANDLE_FILE_BYTE_COUNT];
	    int len;
	    try {
	        digest = MessageDigest.getInstance(MD5_ALGORITHM);
	        in = new FileInputStream(file);

	        while ((len = in.read(buffer)) != NOT_DATA_INDEX) {
	            digest.update(buffer, 0, len);
	        }
	        BigInteger bigInteger = new BigInteger(POSITIVE_SIGN, digest.digest());
	        return bigInteger.toString(HEXADECIMAL);
	    } catch (NoSuchAlgorithmException e) {
	        System.out.println(e.getMessage());
	        return null;
	    } catch (FileNotFoundException e) {
	    	System.out.println(e.getMessage());
	        return null;
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	        return null;
	    } finally {
	        try {
	            in.close();
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
	    }
	}
}
