package test_project;

/**
 * 静态方法操作初始化对象，则每次执行该静态方法都会进行初始化操作
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
		System.out.println("执行test_static_class构造方法");
	}
}
