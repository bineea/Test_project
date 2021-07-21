package test_project;

import org.json.JSONObject;
import org.json.XML;

/**
 * @author guowenbin9
 * 通过JSON-java将xml转换为json
 * 
 * https://github.com/stleary/JSON-java
 * 
 * 备注：
 * 1.org.json与jsonassert的间接依赖com.vaadin.external.google.android-json冲突，类的全限定名完全一致；但是并不能简单的通过排除包来解决
 * 2.需要考虑的问题是jsonassert已经很久不更新了，最新的版本为2017年
 */
public class Test_xml2Json {
	
	public static void main(String[] args) throws Exception {
		String xmlStr = "<message><uuid>a346ae3c-0865-46be-8905-815871824e0c</uuid><source>laswms3.0</source><topic>19</topic><bussinessNo>ESL44002472278828</bussinessNo><header></header><body><pivotFlow><orderId>ESL44002472278828</orderId><operPersonId>tanhuilin</operPersonId><operTime>Sun May 23 00:00:51 CST 2021</operTime><status>19</status><appendix></appendix><dcNo>601</dcNo><whName>天津大件运营中心</whName><whType>2</whType><outboundStatus>0</outboundStatus></pivotFlow></body></message>";
		JSONObject jsonObj = XML.toJSONObject(xmlStr);
		System.out.println(jsonObj.toString());
		JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
	    System.out.println(jo.toString());
	}

}
