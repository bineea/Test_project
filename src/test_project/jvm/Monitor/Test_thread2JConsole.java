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
			//同步代码块
			synchronized (lockObject) {
				try {
					System.out.println("进入同步代码块，并操作对象lockObject实现线程等待");
					lockObject.wait();
					System.out.println("当前线程已被唤醒");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "testLockThread");
		thread.start();
	}
	
	public static void notifyThread(final Object lockObject) throws Exception {
		synchronized (lockObject) {
			System.out.println("进入同步代码块，等待1秒后唤醒线程");
			Thread.sleep(1000);
			System.out.println("操作对象lockObject唤醒线程");
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
				System.out.println("进入第一层同步代码块");
				synchronized (Integer.valueOf(b)) {
					System.out.println("进入第二层同步代码块");
					System.out.println("输出a+b="+ (a+b));
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
		 * 线程死循环演示
		 * Object lockObject1 = new Object(); 
		 * createLockThread(lockObject1);
		 * System.out.println("等待1秒，确保线程已经完成等待操作"); 
		 * Thread.sleep(1000);
		 * notifyThread(lockObject1); 
		 * System.out.println("等待1秒，确保所有子线程已经结束");
		 * Thread.sleep(1000); System.out.println("------------主线程结束--------");
		 */
		
		/*
		 * 线程阻塞演示
		 * BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 * br.readLine(); 
		 * createBusyThread(); 
		 * br.readLine(); 
		 * Object obj = new Object();
		 * createLockThread(obj);
		 */
		
		/*
		 * 线程死锁演示
		 * Object obj1 = new Object(); 
		 * Object obj2 = new Object(); 
		 * SynAddThread(obj1, obj2);
		 * Thread.sleep(500); 
		 * SynAddThread(obj2, obj1);
		 */
		
		/*
		 * 线程死锁演示
		 * for(int i=0; i<100; i++) { 
		 * new Thread(new SynAddRunable(1, 2)).start(); 
		 * new Thread(new SynAddRunable(2, 1)).start(); 
		 * }
		 */
	}
}
