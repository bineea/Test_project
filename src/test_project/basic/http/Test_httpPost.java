package test_project.basic.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test_httpPost {
	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		HttpPost post = new HttpPost("http://10.91.159.41:8000/Management/BaseDataManagement.ashx");
//		String result = "["
//				+ "{\"companyId\":\"RY\",\"companyName\":\"ƽ̨��˾����\","
//				+ "\"identifier\":\"XXXXXXXXXXXXXXXXXX\",\"contactPhone\":\"������ϵ�绰\","
//				+ "\"address\":\"������������\",\"businessScope\":\"��Ӫ��Χ\",\"contactAddress\":\"ͨѶ��ַ\","
//				+ "\"economicType\":\"��Ӫҵ����������\",\"regCapital\":\"ע���ʱ�\",\"legalName\":\"���˴�������\","
//				+ "\"legalID\":\"���˴������֤��\",\"legalPhone\":\"���˴���绰\",\"legalPhoto\":\"���˴������֤ɨ����ļ����\","
//				+ "\"identifierPhoto\":\"Ӫҵִ�ո���ɨ����ļ����\",\"state\":0,\"flag\":1,\"upateTime\":\"2011-10-01 08:30:00\","
//				+ "\"gzAd\":\"���ݰ칫�ص�\",\"responsible\":\"����������\",\"responsibleWay\":\"�����˵绰\",\"parentCompany\":\"ĸ��˾����\","
//				+ "\"parentAd\":\"ĸ��˾�칫��ַ\",\"postCode\":\"123123\",\"serviceItem\":\"������Ŀ\",\"servicePromise\":\"������׼�ͳ�ŵ\"},"
//				+ "{\"companyId\":\"RY\",\"companyName\":\"ƽ̨��˾����\","
//				+ "\"identifier\":\"XXXXXXXXXXXXXXXXXX\",\"contactPhone\":\"������ϵ�绰\","
//				+ "\"address\":\"������������\",\"businessScope\":\"��Ӫ��Χ\",\"contactAddress\":\"ͨѶ��ַ\","
//				+ "\"economicType\":\"��Ӫҵ����������\",\"regCapital\":\"ע���ʱ�\",\"legalName\":\"���˴�������\","
//				+ "\"legalID\":\"���˴������֤��\",\"legalPhone\":\"���˴���绰\",\"legalPhoto\":\"���˴������֤ɨ����ļ����\","
//				+ "\"identifierPhoto\":\"Ӫҵִ�ո���ɨ����ļ����\",\"state\":0,\"flag\":1,\"upateTime\":\"2017-01-16 11:09:00\","
//				+ "\"gzAd\":\"���ݰ칫�ص�\",\"responsible\":\"����������\",\"responsibleWay\":\"�����˵绰\",\"parentCompany\":\"ĸ��˾����\","
//				+ "\"parentAd\":\"ĸ��˾�칫��ַ\",\"postCode\":\"123123\",\"serviceItem\":\"������Ŀ\",\"servicePromise\":\"������׼�ͳ�ŵ\"}"
//				+ "]";
		String result = "{\"items\":[{\"payId\":\"֧������Z2000133000019\",\"countDate\":30.0,\"prepareBank\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\","
				+ "\"flag\":1,\"payName\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\",\"symbol\":\"XX1\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"��Լ�����˷���\","
				+ "\"state\":0,\"payType\":\"΢�š������������ۡ�����wap������֧��������֧����֧����\",\"companyId\":\"XX\"},"
				+ "{\"payId\":\"΢�ţ�Z2000444000013\",\"countDate\":30.0,\"prepareBank\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\","
				+ "\"flag\":1,\"payName\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\",\"symbol\":\"XX2\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"��Լ�����˷���\","
				+ "\"state\":0,\"payType\":\"΢�š������������ۡ�����wap������֧��������֧����֧����\",\"companyId\":\"XX\"},"
				+ "{\"payId\":\"����֧��:Z2002933000017\",\"countDate\":30.0,\"prepareBank\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\",\"flag\":1,\"payName\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\","
				+ "\"symbol\":\"XX3\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"��Լ�����˷���\",\"state\":0,\"payType\":\"΢�š������������ۡ�����wap������֧��������֧����֧����\",\"companyId\":\"XX\"},"
				+ "{\"payId\":\"�����������ۡ�����wap������֧��:Z2001444000011\",\"countDate\":30.0,\"prepareBank\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\",\"flag\":1,\"payName\":\"�������йɷ����޹�˾�����ֺ�·֧�У��˺ţ�120907840310202\","
				+ "\"symbol\":\"XX4\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"��Լ�����˷���\",\"state\":0,\"payType\":\"΢�š������������ۡ�����wap������֧��������֧����֧����\",\"companyId\":\"XX\"}]}";
		List<NameValuePair>list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("fromSource", "BASIC"));
		list.add(new BasicNameValuePair("cmd", "PTZF"));
		list.add(new BasicNameValuePair("key", ""));
		list.add(new BasicNameValuePair("userId", ""));
		list.add(new BasicNameValuePair("MerchantCode", "XX"));
		list.add(new BasicNameValuePair("args", result));
		
		post.setEntity(new UrlEncodedFormEntity(list, StandardCharsets.UTF_8));
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		clientBuilder.setProxy(proxy);
		CloseableHttpClient client = clientBuilder.build();
		HttpResponse response = client.execute(post);
		System.out.println("״̬��Ϊ��"+response.getStatusLine().getStatusCode());
		System.out.println("��������Ϊ��"+EntityUtils.toString(response.getEntity()));
		InputStream ins = response.getEntity().getContent();
		byte[] s = new byte[1024];
		while(ins.read(s,0,1024) != -1)
		{
			String str = new String(s,"UTF-8");
			System.out.println(str);
		}
	}
}
