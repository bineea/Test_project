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
//				+ "{\"companyId\":\"RY\",\"companyName\":\"平台公司名称\","
//				+ "\"identifier\":\"XXXXXXXXXXXXXXXXXX\",\"contactPhone\":\"紧急联系电话\","
//				+ "\"address\":\"行政区划代码\",\"businessScope\":\"经营范围\",\"contactAddress\":\"通讯地址\","
//				+ "\"economicType\":\"经营业户经济类型\",\"regCapital\":\"注册资本\",\"legalName\":\"法人代表姓名\","
//				+ "\"legalID\":\"法人代表身份证号\",\"legalPhone\":\"法人代表电话\",\"legalPhoto\":\"法人代表身份证扫描件文件编号\","
//				+ "\"identifierPhoto\":\"营业执照副本扫描件文件编号\",\"state\":0,\"flag\":1,\"upateTime\":\"2011-10-01 08:30:00\","
//				+ "\"gzAd\":\"广州办公地点\",\"responsible\":\"负责人姓名\",\"responsibleWay\":\"负责人电话\",\"parentCompany\":\"母公司名称\","
//				+ "\"parentAd\":\"母公司办公地址\",\"postCode\":\"123123\",\"serviceItem\":\"服务项目\",\"servicePromise\":\"质量标准和承诺\"},"
//				+ "{\"companyId\":\"RY\",\"companyName\":\"平台公司名称\","
//				+ "\"identifier\":\"XXXXXXXXXXXXXXXXXX\",\"contactPhone\":\"紧急联系电话\","
//				+ "\"address\":\"行政区划代码\",\"businessScope\":\"经营范围\",\"contactAddress\":\"通讯地址\","
//				+ "\"economicType\":\"经营业户经济类型\",\"regCapital\":\"注册资本\",\"legalName\":\"法人代表姓名\","
//				+ "\"legalID\":\"法人代表身份证号\",\"legalPhone\":\"法人代表电话\",\"legalPhoto\":\"法人代表身份证扫描件文件编号\","
//				+ "\"identifierPhoto\":\"营业执照副本扫描件文件编号\",\"state\":0,\"flag\":1,\"upateTime\":\"2017-01-16 11:09:00\","
//				+ "\"gzAd\":\"广州办公地点\",\"responsible\":\"负责人姓名\",\"responsibleWay\":\"负责人电话\",\"parentCompany\":\"母公司名称\","
//				+ "\"parentAd\":\"母公司办公地址\",\"postCode\":\"123123\",\"serviceItem\":\"服务项目\",\"servicePromise\":\"质量标准和承诺\"}"
//				+ "]";
		String result = "{\"items\":[{\"payId\":\"支付宝：Z2000133000019\",\"countDate\":30.0,\"prepareBank\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\","
				+ "\"flag\":1,\"payName\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\",\"symbol\":\"XX1\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"网约车客运服务\","
				+ "\"state\":0,\"payType\":\"微信、银联代付代扣、银联wap、银联支付、连连支付、支付宝\",\"companyId\":\"XX\"},"
				+ "{\"payId\":\"微信：Z2000444000013\",\"countDate\":30.0,\"prepareBank\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\","
				+ "\"flag\":1,\"payName\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\",\"symbol\":\"XX2\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"网约车客运服务\","
				+ "\"state\":0,\"payType\":\"微信、银联代付代扣、银联wap、银联支付、连连支付、支付宝\",\"companyId\":\"XX\"},"
				+ "{\"payId\":\"连连支付:Z2002933000017\",\"countDate\":30.0,\"prepareBank\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\",\"flag\":1,\"payName\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\","
				+ "\"symbol\":\"XX3\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"网约车客运服务\",\"state\":0,\"payType\":\"微信、银联代付代扣、银联wap、银联支付、连连支付、支付宝\",\"companyId\":\"XX\"},"
				+ "{\"payId\":\"银联代付代扣、银联wap、银联支付:Z2001444000011\",\"countDate\":30.0,\"prepareBank\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\",\"flag\":1,\"payName\":\"招商银行股份有限公司广州林和路支行，账号：120907840310202\","
				+ "\"symbol\":\"XX4\",\"updateTime\":\"2017-01-25 10:51:47\",\"payScope\":\"网约车客运服务\",\"state\":0,\"payType\":\"微信、银联代付代扣、银联wap、银联支付、连连支付、支付宝\",\"companyId\":\"XX\"}]}";
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
		System.out.println("状态码为："+response.getStatusLine().getStatusCode());
		System.out.println("返回内容为："+EntityUtils.toString(response.getEntity()));
		InputStream ins = response.getEntity().getContent();
		byte[] s = new byte[1024];
		while(ins.read(s,0,1024) != -1)
		{
			String str = new String(s,"UTF-8");
			System.out.println(str);
		}
	}
}
