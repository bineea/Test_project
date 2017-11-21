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
		System.out.println("list的大小为："+list.size());
		for(String s:list)
		{
			System.out.println(s);
		}
		
		List<String> str = null;
		if(str == null || str.isEmpty())
			System.out.println("list为null！");
//		if(str.isEmpty())
//			System.out.println("list为空！");
		List<String> strs = new ArrayList<String>();
		if(strs == null || strs.isEmpty())
			System.out.println("审核通过");
		else
			System.out.println("审核失败");
	}
}
