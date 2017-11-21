package test_project;

import java.util.Calendar;

public class Test_getDate {
	public static void main(String args[]){
		Calendar c = Calendar.getInstance();
		int date = c.get(Calendar.DATE);
		System.out.println(date);
	}
}
