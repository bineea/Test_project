package test_project;

public class Test_extends_over {

	public static void main(String[] args) {
		child1 c1 = new child1();
		child2 c2 = new child2();
		c1.setLastName("test1");
		System.out.println("c1:"+c1.getLastName());
		System.out.println("c2:"+c2.getLastName());
	}
}

class child1 extends father {
	
	public synchronized void doSomething() {
		System.out.println("����child1�������������");
		super.doSomething();
	}
}

class child2 extends father {
	
	public synchronized void doSomething() {
		System.out.println("����child2�������������");
		super.doSomething();
	}
}


class father {
	
	protected String lastName;
	
	public synchronized void doSomething() {
		System.out.println("���෽�����������");
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
