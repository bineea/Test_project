package test_project.jvm.Monitor;

public class Test_thread2JConsole {

	public static void createBusyThread() {
		Thread thread = new Thread(() -> {
			while(true);
		}, "testBusyThread");
		thread.start();
	}
	
	public static void createLockThread(final Object lockObject) {
		Thread thread = new Thread(() -> {
			//ͬ�������
			synchronized (lockObject) {
				try {
					System.out.println("����ͬ������飬����������lockObjectʵ���̵߳ȴ�");
					lockObject.wait();
					System.out.println("��ǰ�߳��ѱ�����");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "testLockThread");
		thread.start();
	}
	
	public static void notifyThread(final Object lockObject) throws Exception {
		synchronized (lockObject) {
			System.out.println("����ͬ������飬�ȴ�1������߳�");
			Thread.sleep(1000);
			System.out.println("��������lockObject�����߳�");
			lockObject.notifyAll();
		}
	}
	
	static class SynAddRunable implements Runnable {
		
		int a,b;
		
		public SynAddRunable(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public void run() {
			synchronized (Integer.valueOf(a)) {
				System.out.println("�����һ��ͬ�������");
				synchronized (Integer.valueOf(b)) {
					System.out.println("����ڶ���ͬ�������");
					System.out.println("���a+b="+ (a+b));
				}
			}
		}
		
	}
	
	public static void SynAddThread(Object obj1, Object obj2) {
		Thread thread = new Thread(() -> {
			synchronized (obj1) {
				try {
					Thread.sleep(1000);
					synchronized (obj2) {
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "testSynAddThread");
		thread.start();
	}
	
	public static void main(String[] args) throws Exception {
		/*
		 * �߳���ѭ����ʾ
		 * Object lockObject1 = new Object(); 
		 * createLockThread(lockObject1);
		 * System.out.println("�ȴ�1�룬ȷ���߳��Ѿ���ɵȴ�����"); 
		 * Thread.sleep(1000);
		 * notifyThread(lockObject1); 
		 * System.out.println("�ȴ�1�룬ȷ���������߳��Ѿ�����");
		 * Thread.sleep(1000); System.out.println("------------���߳̽���--------");
		 */
		
		/*
		 * �߳�������ʾ
		 * BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 * br.readLine(); 
		 * createBusyThread(); 
		 * br.readLine(); 
		 * Object obj = new Object();
		 * createLockThread(obj);
		 */
		
		/*
		 * �߳�������ʾ
		 * Object obj1 = new Object(); 
		 * Object obj2 = new Object(); 
		 * SynAddThread(obj1, obj2);
		 * Thread.sleep(500); 
		 * SynAddThread(obj2, obj1);
		 */
		
		/*
		 * �߳�������ʾ
		 * for(int i=0; i<100; i++) { 
		 * new Thread(new SynAddRunable(1, 2)).start(); 
		 * new Thread(new SynAddRunable(2, 1)).start(); 
		 * }
		 */
	}
}
