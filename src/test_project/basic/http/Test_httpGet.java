package test_project.basic.http;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Test_httpGet {
	
	public static void main(String[] args) throws ClientProtocolException, IOException
	{
		HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		HttpGet get11 = new HttpGet("http://localhost:8080/ttcp/app/common/welcome/sso?tokenId=123212321");
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		clientBuilder.setProxy(proxy);
		CloseableHttpClient client = clientBuilder.build();
		HttpResponse response = client.execute(get11);
		System.out.println("状态码为："+response.getStatusLine().getStatusCode());
		System.out.println("返回内容为："+EntityUtils.toString(response.getEntity()));
	}
}
