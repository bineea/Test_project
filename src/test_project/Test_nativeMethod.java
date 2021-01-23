package test_project;

/**
 * 实现JNI,即Java Native Interface方法
 * @author binee
 *
 */
public class Test_nativeMethod {
	
	//通过native关键字定义native方法，且不提供方法实现，相当于java interface；因为方法实现由非java语言实现
	private native void helloworld();
	
	public static void main(String[] args) {
		Test_nativeMethod test = new Test_nativeMethod();
		test.helloworld();
	}

	
}
