package test_project;

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

public class Test_httpPost2 {
	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		HttpPost post = new HttpPost("http://jiekou.hzcb.gov.cn/carif/app/common/api");
		String result = "{\"issue\":\"201701\",\"statDataType\":\"A_PERSON\"}";
		List<NameValuePair>list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("type", "REQ_STAT_PERSON_AREA"));
		list.add(new BasicNameValuePair("jsonStr", result));
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
