package test_project.jvm.GC;

//������Minor GC
//-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
//-Xms20M -Xmx20M -Xmn10M����������������java�Ѵ�СΪ20M��������չ������������10M��������10M
//-XX:survivorRatio=8�趨��������Eden����һ��Survivor���Ŀռ����Ϊ8:1


//Parallel Scavenge�ռ���û�д���GC!!!!!ʲô������
public class Test_gc_MinorGC {
	
	private static final int _1MB = 1024 * 1024;
	
	public static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		//System.gc();
		allocation4 = new byte[4 * _1MB];
		
	}
	
	public static void main(String[] args) {
		
		Test_gc_MinorGC.testAllocation();
	}
}
