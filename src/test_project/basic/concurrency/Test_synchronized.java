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
					System.out.println("ִ��"+i+"��ʼ��ʱ�����"+System.currentTimeMillis());
					lock.wait(1000);
					System.out.println("ִ��"+i+"��ɣ�wait����������ǰ�̣߳�ʱ�����"+System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * ͨ��jol-core�鿴java�����ڴ沼��
	 */
	public void testSynchronizedHeader() {
		Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
	}
	

}
