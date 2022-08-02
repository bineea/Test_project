package test_project;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Test_table {

	
	public static void main(String[] args) {
		Table<Integer, String, Object> priorityDataTable = HashBasedTable.create();
		priorityDataTable.put(1, "1", "");
		System.out.println(String.valueOf(priorityDataTable.get(1, "1")));
//		priorityDataTable.put(1, "1", null);
//		System.out.println(String.valueOf(priorityDataTable.get(1, "1")));
		Test_table test = new Test_table();
		test.testNeedTime();
	}
	
	public void testNeedTime() {
		Map<Integer, String> map = new HashMap();
		Long startTime = System.currentTimeMillis();
		for (int i=1; i<= 400000; i++) {
			String value = UUID.randomUUID().toString();
			map.put(i, value);
			System.out.println(i + "£º" + value);
		}
		for (int i=1; i<= 400000; i++) {
			String value = map.get(i);
			System.out.println(i + "£º" + value);
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("¹²ºÄÊ±(ms)£º"+ (endTime - startTime));
	}
}
