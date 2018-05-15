package test_project;

public class Test_extends_over {

	public static void main(String[] args) {
		child c = new child();
		c.doSomething();
	}
}

class child extends father {
	
	public synchronized void doSomething() {
		System.out.println("子类方法输出。。。");
		super.doSomething();
	}
}

class father {
	
	public synchronized void doSomething() {
		System.out.println("父类方法输出。。。");
	}
}
