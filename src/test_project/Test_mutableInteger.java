package test_project;

/**
 * synchronized是对该对象进行加锁，一个对象只有一把锁；若要多线程访问同一个对象的不同synchronized方法，也必须等待该线程执行完成释放锁，才能再次给对象加锁
 */
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