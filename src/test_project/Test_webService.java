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

public class Test_webService {
	
	private static char[] commonDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public static void main(String[] args)
	{
		try
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
			if(result_mt.startsWith("-")||result_mt.equals(""))//���Ͷ��ţ�������Ը��ſ�ͷ���Ƿ���ʧ�ܡ�
			{
				System.out.println("����ʧ�ܣ�����ֵΪ��"+result_mt+"��鿴webservice����ֵ���ձ�");
				return;
			}
			//������ر�ʶ��ΪС��19λ��������String���͵ġ���¼�����͵����Ρ�
			else
			{
				System.out.println("���ͳɹ�������ֵΪ��"+result_mt);
			}
			String ba = wss.balance(sn, pwd);
			System.out.println(ba);
		}
		catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String hash(InputStream in)
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
	
	private static String toHexString(byte[] b)
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
