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
		test.list2Remove();
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
	
	
	/**
	 * remove all 将会把重复的值全部移除
	 */
	private void list2Remove() {
		List<Integer> list1 = new ArrayList<>();
		list1.add(6);
		list1.add(1);
		list1.add(10);
		list1.add(3);
		list1.add(null);
		list1.add(0);
		list1.add(16);
		list1.add(26);
		list1.add(6);
		list1.add(5);
		
		List<Integer> list2 = new ArrayList<>();
		list2.add(6);
		list2.add(1);
		list2.add(10);
		list2.add(3);
		list2.add(null);
		
		List<Integer> list3 = new ArrayList<>();
		list2.add(60);
		list2.add(10);
		list2.add(100);
		list2.add(30);
		
		List<Integer> list4 = new ArrayList<>();
		list4.add(0);
		list4.add(16);
		list4.add(26);
		list4.add(5);
		list4.add(30);

		list1.removeAll(list2);
		for (Integer i : list1) {
			System.out.println("remove list2："+i);
		}
		list1.removeAll(list3);
		for (Integer i : list1) {
			System.out.println("remove list3："+i);
		}
		list1.removeAll(list4);
		for (Integer i : list1) {
			System.out.println("remove list4："+i);
		}
	}
}
