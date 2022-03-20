package test_project.basic.concurrency;

public class Test_synchronized {

	private Object lock = new Object();
	
	public void testWait() {
		
		for (int i=0; i<5; i++) {
			synchronized(lock) {
				try {
					System.out.println("执行"+i+"开始，时间戳："+System.currentTimeMillis());
					lock.wait(1000);
					System.out.println("执行"+i+"完成，wait方法阻塞当前线程，时间戳："+System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Test_synchronized test = new Test_synchronized();
		test.testWait();
	}
}
