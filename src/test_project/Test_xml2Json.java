package test_project;

import org.json.JSONObject;
import org.json.XML;

/**
 * @author guowenbin9
 * ͨ��JSON-java��xmlת��Ϊjson
 * 
 * https://github.com/stleary/JSON-java
 * 
 * ��ע��
 * 1.org.json��jsonassert�ļ������com.vaadin.external.google.android-json��ͻ�����ȫ�޶�����ȫһ�£����ǲ����ܼ򵥵�ͨ���ų��������
 * 2.��Ҫ���ǵ�������jsonassert�Ѿ��ܾò������ˣ����µİ汾Ϊ2017��
 */
public class Test_xml2Json {
	
	public static void main(String[] args) throws Exception {
		String xmlStr = "<message><uuid>a346ae3c-0865-46be-8905-815871824e0c</uuid><source>laswms3.0</source><topic>19</topic><bussinessNo>ESL44002472278828</bussinessNo><header></header><body><pivotFlow><orderId>ESL44002472278828</orderId><operPersonId>tanhuilin</operPersonId><operTime>Sun May 23 00:00:51 CST 2021</operTime><status>19</status><appendix></appendix><dcNo>601</dcNo><whName>�������Ӫ����</whName><whType>2</whType><outboundStatus>0</outboundStatus></pivotFlow></body></message>";
		JSONObject jsonObj = XML.toJSONObject(xmlStr);
		System.out.println(jsonObj.toString());
		JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
	    System.out.println(jo.toString());
	}

}
