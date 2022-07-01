package test_project.basic.concurrency;

import org.openjdk.jol.info.ClassLayout;

public class Test_synchronized {

	private Object lock = new Object();
	
	public static void main(String[] args) {
		
		Test_synchronized test = new Test_synchronized();
		test.testSynchronizedHeader();
	}
	
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
	
	/**
	 * 通过jol-core查看java对象内存布局
	 */
	public void testSynchronizedHeader() {
		Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
	}
	

}
