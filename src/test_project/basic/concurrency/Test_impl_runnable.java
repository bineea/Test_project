package test_project.basic.concurrency;

public class Test_impl_runnable implements Runnable {

	@Override
	public void run() {
		System.out.println("ִ��run����");
	}

	public static void main(String[] args) {
		// ִ��run����
		new Test_impl_runnable().run();
		// �����߳�
		new Thread(new Test_impl_runnable()).start();
	}
}
