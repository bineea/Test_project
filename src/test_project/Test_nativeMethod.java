package test_project;

/**
 * ʵ��JNI,��Java Native Interface����
 * @author binee
 *
 * ע�⣺��Ҫ����-Djava.library.path=����Ŀ¼������-Djava.library.path=D:\Project\Java\Test_project\src\test_project
 */
public class Test_nativeMethod {
	
	private static final String JNI_LIB_NAME = "myfirstjnilibrary";
	
	//ͨ��native�ؼ��ֶ���native�������Ҳ��ṩ����ʵ�֣��൱��java interface����Ϊ����ʵ���ɷ�java����ʵ��
	private native void helloworld();
	
    static{
		/*
		 * ��仰���������ڱ����ʱ�򲢲���ȥ��native�������ʵ�֣�������ýӿ�һ����
		 * ������ 
		 * 	1. Linux/Unix/Max �ϣ���ȥ���� lib${JNI_LIB_NAME}.jnilib
		 *  2. Solaris �ϣ���ȥ���� lib${JNI_LIB_NAME}.so
		 *  3. Win �ϣ����� ${JNI_LIB_NAME}.dll, ��û��libǰ׺��
		 */
    	//���뱾�ؿ�
        System.loadLibrary(JNI_LIB_NAME);
    }
	
	public static void main(String[] args) {
		Test_nativeMethod test = new Test_nativeMethod();
		test.helloworld();
	}

	
}
