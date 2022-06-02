package test_project.basic.concurrency;

public class Test_impl_runnable implements Runnable {

	@Override
	public void run() {
		System.out.println("执行run方法");
	}

	public static void main(String[] args) {
		// 执行run方法
		new Test_impl_runnable().run();
		// 启动线程
		new Thread(new Test_impl_runnable()).start();
	}
}
