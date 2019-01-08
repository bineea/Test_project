package test_project;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.security.MessageDigest;

import javax.xml.rpc.ServiceException;

import org.tempuri.WebServiceLocator;
import org.tempuri.WebServiceSoap;

import myblog.manager.service.MyFirstWebservice;
import myblog.manager.service.MyFirstWebserviceImplServiceLocator;
import myblog.manager.service.Role;

public class Test_webService {
	
	private final char[] commonDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public static void main(String[] args)
	{
		Test_webService test = new Test_webService();
		try
		{
//			test.testOrgWebService();
			test.testMyblog();
		}
		catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (UnsupportedEncodingException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void testMyblog() throws RemoteException, ServiceException
	{
		//创建一个用于产生WebServiceImpl实例的工厂，WebServiceImplService类是wsimport工具生成的
		MyFirstWebserviceImplServiceLocator factory = new MyFirstWebserviceImplServiceLocator();
		//通过工厂生成一个WebServiceImpl实例，WebServiceImpl是wsimport工具生成的
		MyFirstWebservice wsImpl = factory.getMyFirstWebserviceImplPort();
		//调用WebService的sayHello方法
		String resResult1 = wsImpl.sayHello();
		System.out.println("调用WebService的sayHello方法返回的结果是："+resResult1);
		System.out.println("---------------------------------------------------");
		//调用WebService的sayHello方法
		Role[] resResult2 = wsImpl.findRoleByName("管理");
		System.out.println("调用WebService的findRoleByName方法返回的结果是："+resResult2.length);
		System.out.println("---------------------------------------------------");
	}
	
	public void testOrgWebService() throws ServiceException, UnsupportedEncodingException, RemoteException
	{
		String sn="SDK-GGH-010-00033";
		String password="690462";
		String mobiles="15202213603,13501148192";
		String content=URLEncoder.encode("test", "utf8");
		
		WebServiceLocator webServiceLocator = new WebServiceLocator();
		WebServiceSoap wss = webServiceLocator.getWebServiceSoap();
		String result = sn+password;
		InputStream in = new ByteArrayInputStream(result.getBytes("UTF-8"));
		String pwd = hash(in).toUpperCase();
		System.out.println("md5:"+pwd);
		String result_mt = wss.mt(sn, pwd, mobiles, content, "", "", "");
		if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
		{
			System.out.println("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			return;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		else
		{
			System.out.println("发送成功，返回值为："+result_mt);
		}
		String ba = wss.balance(sn, pwd);
		System.out.println(ba);
	}
	
	public String hash(InputStream in)
	{
		try
		{
			byte[] buffer = new byte[1024];
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int numRead = 0;
			while ((numRead = in.read(buffer)) > 0)
			{
				md5.update(buffer, 0, numRead);
			}
			in.close();
			return toHexString(md5.digest());
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	private String toHexString(byte[] b)
	{
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++)
		{
			sb.append(commonDigit[(b[i] & 0xf0) >>> 4]);
			sb.append(commonDigit[b[i] & 0x0f]);
		}
		return sb.toString();
	}
}
