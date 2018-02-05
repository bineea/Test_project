package test_project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test_listAddNull {
	
	public static void main(String[] args){
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("");
		System.out.println("list�Ĵ�СΪ��"+list.size());
		list.stream().forEach(System.out::println);
		
		Test_listAddNull t = new Test_listAddNull();
		do
		{
			t.limitList(list);
			System.out.println("list�Ĵ�СΪ��"+list.size());
		}
		while(!list.isEmpty() && list != null);
		
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
	
	public void limitList(List<String> list)
	{
		List<String> operateList = list.stream().limit(2).collect(Collectors.toList());
		operateList.stream().forEach(list::remove);
		list.stream().forEach(System.out::println);
	}
}
