package test_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test_list {

	public static void main(String[] args) {
		

	}
	
	public void list2Sort() {
		List<Integer> list = new ArrayList<>();
		list.add(6);
		list.add(1);
		list.add(10);
		list.add(3);
		list.add(null);
		list.add(0);
		list.add(16);
		list.add(26);
		list.add(6);
		list.add(5);
		
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o2 == null) {
					return -1;
				}
				if(o1 == null) {
					return 1;
				}
				return o2 - o1;
			}
			
		});
		
		System.out.println(list.toString());
	}
	
	public void listIsNull() {
		List<List> testList = null;
		//如果不进行非空校验，直接循环，会抛出空指针异常
		if(testList != null && !testList.isEmpty()) {
			for(List list : testList) {
				System.out.println(list.toString());
			}
		}
		System.out.println("done");
	}
}
