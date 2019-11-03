package test_project.basic.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test_SendBinFile {

	final static Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));  
	final static String url = "http://wycapi.gzjt.gov.cn/api/app/common/binapi";

    public static String post(String strURL, Map<String, String> textMap, Map<String, String> fileMap)
    {
        String BOUNDARY = "inputstream"; //boundary就是request头和上传文件内容的分隔符 
        try
        {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection)url.openConnection(proxy);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
            connection.setRequestProperty("Accept", "text/html"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Encoding", "gzip"); // 设置接收数据的格式
            connection.setRequestProperty("binfile-auth", "40a59c8a385745f4baccb254224cc1a5");
            connection.setRequestProperty("binfile-gzip", "false");
            connection.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + BOUNDARY); // 设置发送数据的格式
            connection.connect();
            OutputStream out = new DataOutputStream(connection.getOutputStream()); // utf-8编码

            if(textMap != null)
            {
                StringBuffer strBuf = new StringBuffer();
                Iterator<Map.Entry<String, String>> iter = textMap.entrySet().iterator();
                while(iter.hasNext())
                {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = (String)entry.getKey();
                    String inputValue = (String)entry.getValue();
                    if(inputValue == null)
                    {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n");
                    strBuf.append("Content-Type: text/plain; charset=UTF-8\r\n");
                    strBuf.append("Content-Transfer-Encoding: 8bit\r\n\r\n");
                    strBuf.append(inputValue);
                }
                System.out.println(strBuf.toString());
                out.write(strBuf.toString().getBytes("UTF-8"));
            }

            // file  
            if(fileMap != null)
            {
                Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
                while(iter.hasNext())
                {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = (String)entry.getKey();
                    String inputValue = (String)entry.getValue();
                    if(inputValue == null)
                    {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + "application/octet-stream" + "\r\n");
                    strBuf.append("Content-Transfer-Encoding: " + "binary" + "\r\n\r\n");

                    System.out.println(strBuf.toString());
                    out.write(strBuf.toString().getBytes("UTF-8"));

                    DataInputStream in = new DataInputStream(new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while((bytes = in.read(bufferOut)) != -1)
                    {
                        out.write(bufferOut, 0, bytes);
                        System.out.println("===="+new String(bufferOut,"UTF-8"));
                    }
                    in.close();
                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取响应
            int length = (int)connection.getContentLength();// 获取长度
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);
            InputStream is = null;
            if(responseCode==200){
            	is = connection.getInputStream();
            }else{
            	is = connection.getErrorStream();
            }
            if(length != -1)
            {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while((readLen = is.read(temp)) > 0)
                {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                System.out.println(result);
                return result;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    public static void main(String[] args)
    {
        String filepath = "C:/Users/Administrator/Desktop/RY_BASIC_PTJB_REQ_20161205145100348.json";
        Map<String, String> textMap = new HashMap<String, String>();
        textMap.put("filename", "RY_BASIC_PTJB_REQ_20161205145100348.json");
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("binFile", filepath);
        post(url, textMap, fileMap);
    }
}
