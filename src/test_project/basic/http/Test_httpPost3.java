package test_project.basic.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Test_httpPost3 {

	
	public static void main(String[] args) {
		Test_httpPost3 test = new Test_httpPost3();
		
		// UAT
		String url = "https://uat-cloud-api.jdl.cn/api/ByteDance/waybill/queryTrack?LOP-DN=uat-cloud-api.jdl.cn";
		String jsonStr = "{\"app_id\":\"B153E00C18650205CC4BA9438C6DD5A7\",\"param\":\"{\\\"trackNo\\\": \\\"JDLD10382985553\\\"}\",\"timestamp\":\"1631797230110\",\"v\":\"1.0\",\"sign\":\"6147c62549a5ebc55751c4b7593f7b89\"}";
		
		// TEST
		
//		String url = "https://test-cloud-api.jdl.cn/api/ByteDance/waybill/queryTrack";
//		String jsonStr = "{\"app_id\":\"B153E00C18650205CC4BA9438C6DD5A7\",\"param\":\"{\\\"trackNo\\\": \\\"JDLD10172562209\\\"}\",\"timestamp\":\"1631797230110\",\"v\":\"1.0\",\"sign\":\"6147c62549a5ebc55751c4b7593f7b89\"}";
		
//		String url = "https://test-cloud-api.jdl.cn/api/ByteDance/waybill/queryTrack";
//		String jsonStr = "{\"app_id\":\"B153E00C18650205CC4BA9438C6DD5A7\",\"param\":\"{\\\"trackNo\\\": \\\"JDLD10172570137\\\"}\",\"timestamp\":\"1631797230110\",\"v\":\"1.0\",\"sign\":\"6147c62549a5ebc55751c4b7593f7b89\"}";
		
		try {
			test.httpPostRaw(url, jsonStr, null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void httpPostRaw(String url, String jsonStr, Map<String,String> headers, Charset encode) throws IOException {
		
		if(encode == null){  
            encode = StandardCharsets.UTF_8;  
        }
		
		HttpPost post = new HttpPost(url);
		
		post.setHeader("Content-type", "application/json");    
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
            	post.setHeader(entry.getKey(),entry.getValue());
            }
        }
		post.setEntity(new StringEntity(jsonStr, encode));

		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		CloseableHttpClient client = clientBuilder.build();
		HttpResponse response = client.execute(post);
		System.out.println("状态码为："+response.getStatusLine().getStatusCode());
		System.out.println("返回内容为："+EntityUtils.toString(response.getEntity()));
		InputStream ins = null;
		try {
			ins = response.getEntity().getContent();
			byte[] s = new byte[1024];
			while(ins.read(s,0,1024) != -1)
			{
				String str = new String(s,"UTF-8");
				System.out.println(str);
			}
		} finally {
			if(ins != null) {
				ins.close();
			}
		}
		
		
	}
}
