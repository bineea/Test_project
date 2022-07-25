package test_project;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Test_table {

	
	public static void main(String[] args) {
		Table<Integer, String, Object> priorityDataTable = HashBasedTable.create();
		priorityDataTable.put(1, "1", "");
		System.out.println(String.valueOf(priorityDataTable.get(1, "1")));
		priorityDataTable.put(1, "1", null);
		System.out.println(String.valueOf(priorityDataTable.get(1, "1")));
	}
}
