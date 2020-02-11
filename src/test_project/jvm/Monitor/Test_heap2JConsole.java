package test_project.jvm.Monitor;

import java.util.ArrayList;
import java.util.List;

//-Djava.rmi.server.hostname=127.0.0.1
//-Dcom.sun.management.jmxremote
//-Dcom.sun.management.jmxremote.port=1099
//-Dcom.sun.management.jmxremote.ssl=false
//-Dcom.sun.management.jmxremote.authenticate=false

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
		//fillHeap方法内执行gc，因为对象objectList存在强引用关系（List<OOMObject> objectList = new ArrayList<OOMObject>();），导致objectList不可能回收，则其包含OOMObject对象也无法回收，但是通过执行gc，则将所有新生代数据全部转移至老年代
		//System.gc();
		//System.out.println("------------------fillHeap 方法内执行gc------------------");
		
		System.out.println("------------------wait 50000ms------------------");
		Thread.sleep(50000);
		
		System.out.println("objectList.size:"+objectList.size());
		System.out.println("objectList.get(999).placeholder.length:"+objectList.get(999).placeholder.length);
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("------------------start------------------");
		fillHeap(1000);
		//fillHeap方法外执行gc，因为对象objectList不再存在强引用关系（List<OOMObject> objectList = new ArrayList<OOMObject>();），导致objectList直接回收，则其包含OOMObject对象也直接回收
		//System.gc();
		//System.out.println("------------------fillHeap 方法外执行gc------------------");
		System.out.println("------------------end------------------");
	}

}
