package test_project;

public class Test_extends_over {

	public static void main(String[] args) {
		child c = new child();
		c.doSomething();
	}
}

class child extends father {
	
	public synchronized void doSomething() {
		System.out.println("���෽�����������");
		super.doSomething();
	}
}

class father {
	
	public synchronized void doSomething() {
		System.out.println("���෽�����������");
	}
}
