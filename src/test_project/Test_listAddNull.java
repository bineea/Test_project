package test_project;

import java.util.ArrayList;
import java.util.List;

public class Test_listAddNull {
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("");
		System.out.println("list�Ĵ�СΪ��"+list.size());
		for(String s:list)
		{
			System.out.println(s);
		}
		
		List<String> str = null;
		if(str == null || str.isEmpty())
			System.out.println("listΪnull��");
//		if(str.isEmpty())
//			System.out.println("listΪ�գ�");
		List<String> strs = new ArrayList<String>();
		if(strs == null || strs.isEmpty())
			System.out.println("���ͨ��");
		else
			System.out.println("���ʧ��");
	}
}
