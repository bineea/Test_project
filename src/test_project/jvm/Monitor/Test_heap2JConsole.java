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
		//fillHeap������ִ��gc����Ϊ����objectList����ǿ���ù�ϵ��List<OOMObject> objectList = new ArrayList<OOMObject>();��������objectList�����ܻ��գ��������OOMObject����Ҳ�޷����գ�����ͨ��ִ��gc������������������ȫ��ת���������
		//System.gc();
		//System.out.println("------------------fillHeap ������ִ��gc------------------");
		
		System.out.println("------------------wait 50000ms------------------");
		Thread.sleep(50000);
		
		System.out.println("objectList.size:"+objectList.size());
		System.out.println("objectList.get(999).placeholder.length:"+objectList.get(999).placeholder.length);
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("------------------start------------------");
		fillHeap(1000);
		//fillHeap������ִ��gc����Ϊ����objectList���ٴ���ǿ���ù�ϵ��List<OOMObject> objectList = new ArrayList<OOMObject>();��������objectListֱ�ӻ��գ��������OOMObject����Ҳֱ�ӻ���
		//System.gc();
		//System.out.println("------------------fillHeap ������ִ��gc------------------");
		System.out.println("------------------end------------------");
	}

}
