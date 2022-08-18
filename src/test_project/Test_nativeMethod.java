package test_project;

/**
 * 实现JNI,即Java Native Interface方法
 * @author binee
 *
 * 注意：需要配置-Djava.library.path=所在目录，即：-Djava.library.path=D:\Project\Java\Test_project\src\test_project
 */
public class Test_nativeMethod {
	
	private static final String JNI_LIB_NAME = "myfirstjnilibrary";
	
	//通过native关键字定义native方法，且不提供方法实现，相当于java interface；因为方法实现由非java语言实现
	private native void helloworld();
	
    static{
		/*
		 * 这句话表明我们在编译的时候并不会去管native方法如何实现，就像调用接口一样。
		 * 在这里 
		 * 	1. Linux/Unix/Max 上，会去加载 lib${JNI_LIB_NAME}.jnilib
		 *  2. Solaris 上，会去加载 lib${JNI_LIB_NAME}.so
		 *  3. Win 上，则是 ${JNI_LIB_NAME}.dll, 并没有lib前缀。
		 */
    	//载入本地库
        System.loadLibrary(JNI_LIB_NAME);
    }
	
	public static void main(String[] args) {
		Test_nativeMethod test = new Test_nativeMethod();
		test.helloworld();
	}

	
}
