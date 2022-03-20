package test_project.basic.concurrency;

public class Test_synchronized {

	private Object lock = new Object();
	
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
	
	public static void main(String[] args) {
		
		Test_synchronized test = new Test_synchronized();
		test.testWait();
	}
}
