package test_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test_list {

	public static void main(String[] args) {
		Test_list test = new Test_list();
		test.listToSort();
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
	
	
	private void listToSort() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(20211021, "20211021");
		map.put(20211022, "20211022");
		map.put(20211023, "20211023");
		map.put(20211024, "20211024");
		map.put(20211025, "20211025");
		
		Set<Integer> keySet = map.keySet();
		Iterator<Integer> i = keySet.iterator();
		List<Integer> keyList = new ArrayList();
		while(i.hasNext()) {
			Integer integer = i.next();
			keyList.add(integer);
		}
		
		Collections.sort(keyList);
		System.out.println(keyList);
	}
}
