package test_project;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

/**
 * 手动解析xml，并转换为json
 * @author guowenbin9
 *
 */
public class Test_xml2JsonUtils {

    private Test_xml2JsonUtils() {}

    public static String handleXml2Json(String xml) {
        JSONObject obj = new JSONObject();
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            obj.put(root.getName(), iterateElement(root));
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map iterateElement(Element element) {
        List nodes = element.elements();
        Element et = null;
        Map obj = new HashMap();
        Object temp;
        List list = null;
        for (int i = 0; i < nodes.size(); i++) {
            list = new LinkedList();
            et = (Element) nodes.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.elements().size() == 0) {
                    continue;
                }
                if (obj.containsKey(et.getName())) {
                    temp = obj.get(et.getName());
                    if (temp instanceof List) {
                        list = (List) temp;
                        list.add(iterateElement(et));
                    } else if (temp instanceof Map) {
                        list.add((HashMap) temp);
                        list.add(iterateElement(et));
                    } else {
                        list.add((String) temp);
                        list.add(iterateElement(et));
                    }
                    obj.put(et.getName(), list);
                } else {
                    obj.put(et.getName(), iterateElement(et));
                }
            } else {
                if (obj.containsKey(et.getName())) {
                    temp = obj.get(et.getName());
                    if (temp instanceof List) {
                        list = (List) temp;
                        list.add(et.getTextTrim());
                    } else if (temp instanceof Map) {
                        list.add((HashMap) temp);
                        list.add(iterateElement(et));
                    } else {
                        list.add((String) temp);
                        list.add(et.getTextTrim());
                    }
                    obj.put(et.getName(), list);
                } else {
                    obj.put(et.getName(), et.getTextTrim());
                }

            }

        }
        return obj;
    }

}
