package test_project;

/**
 * ʵ��JNI,��Java Native Interface����
 * @author binee
 *
 */
public class Test_nativeMethod {
	
	//ͨ��native�ؼ��ֶ���native�������Ҳ��ṩ����ʵ�֣��൱��java interface����Ϊ����ʵ���ɷ�java����ʵ��
	private native void helloworld();
	
	public static void main(String[] args) {
		Test_nativeMethod test = new Test_nativeMethod();
		test.helloworld();
	}

	
}
