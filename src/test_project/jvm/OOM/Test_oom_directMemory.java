package test_project.jvm.OOM;

import java.lang.reflect.Field;

import sun.misc.Unsafe;



public class Test_oom_directMemory {
	
	private static final int _1MB = 1024*1024;
	
	public static void main(String[] args) throws Exception
	{
		Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while(true)
		{
			unsafe.allocateMemory(_1MB);
		}
	}

}
