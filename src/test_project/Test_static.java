package test_project;

/**
 * ��̬����������ʼ��������ÿ��ִ�иþ�̬����������г�ʼ������
 * @author guowenbin9
 *
 */
public class Test_static {

	public static Test_static_class testInitClass() {
		return new Test_static_class();
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			Test_static.testInitClass();
		}
	}
	
}

class Test_static_class {
	
	public Test_static_class() {
		System.out.println("ִ��test_static_class���췽��");
	}
}
