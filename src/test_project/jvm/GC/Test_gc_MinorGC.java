package test_project.jvm.GC;

//新生代Minor GC
//-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
//-Xms20M -Xmx20M -Xmn10M这三个参数限制了java堆大小为20M，不可扩展，其中新生代10M，老生代10M
//-XX:survivorRatio=8设定新生代中Eden区与一个Survivor区的空间比例为8:1


//Parallel Scavenge收集器没有触发GC!!!!!
//通过对比发现，当整个新生代剩余的空间无法存放某个对象时，Parallel Scavenge/Parallel Old中该对象会直接进入老年代。而如果整个新生代剩余的空间可以存放但只是Eden区空间不足，则会尝试一次Minor GC。
//而对于Serial/Serial Old当发现Eden区不足以存放对象时，就进行一次Minor GC

public class Test_gc_MinorGC {
	
	private static final int _1MB = 1024 * 1024;
	
	public static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		
//		allocation4 = new byte[1 * _1MB];
//		allocation4 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
		
		Test_gc_MinorGC.testAllocation();
	}
}
