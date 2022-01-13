package test_project.basic.concurrency;

import java.util.ArrayList;
import java.util.List;

public class Test_local_variable_concurrency {
	
	public static void main(String[] args) {
		
		Test_loca_variable testLocalVariable = new Test_loca_variable();
		List<Integer> integerList = new ArrayList<>(500);
		
		for (int i=0; i<500; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Integer x = testLocalVariable.handleDate(10000);					
					integerList.add(x);
				}
				
			}).start();
		}
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Integer i : integerList) {
			System.out.println(i);
		}
	}

}

class Test_loca_variable {
	
	public Integer handleDate(int i) {
		Integer x = Integer.valueOf(i);
		x += 1;
		return x;
	}

}
