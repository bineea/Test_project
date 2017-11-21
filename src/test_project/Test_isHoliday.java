package test_project;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test_isHoliday {
	
	public static void main(String[] args){
//		String httpUrl = "http://apis.baidu.com/xiaogg/holiday/holiday";
		String httpUrl = "http://www.easybots.cn/api/holiday.php";
		String httpArg = "d=20160830";
		String jsonResult = request(httpUrl, httpArg);
		System.out.println("返回结果："+jsonResult);
	}
	
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
//	        connection.setRequestProperty("apikey",  "4a4d46eaaa19d5df1768b7bd48f182c5");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = "qwer"+sbf.toString();
	        Pattern pattern = Pattern.compile("\\d+");
	        Matcher matcher = pattern.matcher(result);
	        while(matcher.find())
	        {
	        	System.out.println(matcher.group());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
