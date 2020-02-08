package test_project.jvm.Monitor;

import java.util.ArrayList;
import java.util.List;

public class Test_heap2JConsole {
	
	private static final int _1MB = 64 * 1024;
	
	static class OOMObject
	{
		public byte[] placeholder = new byte[_1MB];
	}
	
	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> objectList = new ArrayList<OOMObject>();
		for(int i=0; i<num; i++) {
			Thread.sleep(50);
			objectList.add(new OOMObject());
			System.out.println("------------------"+i+"------------------");
		}
		System.gc();
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("------------------start------------------");
		Test_heap2JConsole.fillHeap(1000);
		System.out.println("------------------end------------------");
	}

}
