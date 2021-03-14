package test_project.basic.concurrency;

public class Test_impl_runnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Ö´ÐÐrun·½·¨");
	}

	public static void main(String[] args) {
		new Thread(new Test_impl_runnable()).run();
	}
}
