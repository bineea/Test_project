package test_project;

public class Test_mutableInteger {

	private int value;

	public synchronized int getValue() {
		try {
			System.out.println("get:"+System.currentTimeMillis());
			Thread.sleep(10000);
			System.out.println("get"+value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return value;
	}

	public synchronized void setValue(int value) {
		try {
			System.out.println("set:"+System.currentTimeMillis());
			Thread.sleep(10000);
			System.out.println("set"+value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.value = value;
	}
	
	public static void main(String[] args){
		Test_mutableInteger mu = new Test_mutableInteger();
		mu.setValue(10);
		mu.getValue();
		new GetThread(mu).start();
		new SetThread(mu).start();
	}
}

class GetThread extends Thread {
	private Test_mutableInteger t;
	GetThread(Test_mutableInteger t){
		this.t = t;
	}
	public void run() {
		System.out.println("getThread");
		t.getValue();
	}
}

class SetThread extends Thread {
	private Test_mutableInteger t;
	SetThread(Test_mutableInteger t){
		this.t = t;
	}
	public void run() {
		System.out.println("setThread");
		t.setValue(101);
	}
}