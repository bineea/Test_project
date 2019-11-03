package test_project.basic.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Test_binFile_post {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		HttpPost post = new HttpPost("http://218.67.246.252:9004/travel/app/common/binapi");
		String result = "{\"name\":\"太极\",\"key\":\"1233asd\",\"routeName\":\"156\"}";
		
		InputStream in = new ByteArrayInputStream(result.getBytes("UTF-8"));
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		String filename = "GJCLXX_BUSGPS_SEND_REQ_2018051016000.json";
		builder.addBinaryBody("binFile", in, ContentType.DEFAULT_BINARY, filename);// 文件
		builder.addTextBody("filename", filename, ContentType.create(ContentType.DEFAULT_TEXT.getMimeType(), "UTF-8"));
		post.setEntity(builder.build());
		
		int connTimeout = 10000;
		int soTimeout = 30000;
		
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		if (connTimeout > 0) configBuilder.build();
		if (soTimeout > 0) configBuilder.setSocketTimeout(soTimeout);
		clientBuilder.setDefaultRequestConfig(configBuilder.build());
		// 4.3以后会自动在interceptor中实现启用压缩和自动解压，所以不需要gzip的时候需要指定一下---binfile-gzip true/false
		if (!false) clientBuilder.disableContentCompression();
		clientBuilder.setProxy(proxy);
		CloseableHttpClient client = clientBuilder.build();
		try
		{
			HttpResponse response = client.execute(post);
			System.out.println(response.toString());
			System.out.println(response.getStatusLine().getStatusCode());
			InputStream ins = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			char[] c = new char[40960];
			int read = -1;
			while((read = reader.read(c)) != -1)
			{
				buffer.append(c, 0 , read);
			}
			if (reader != null) reader.close();
			if (ins != null) ins.close();
			System.out.print(buffer.toString());
		}
		catch(SocketTimeoutException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
