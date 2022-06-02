package test_project.basic.concurrency;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test_threadPoolExecutor {
	
	public static void main(String[] args) throws InterruptedException
	{
	    ThreadPoolExecutor myThreadPoolExecutor = new ThreadPoolExecutor(Integer.parseInt("1"), Integer.parseInt("1"), 5L, TimeUnit.SECONDS,
	            new LinkedBlockingQueue<Runnable>(Integer.parseInt("1")), new ThreadPoolExecutor.AbortPolicy());
	    
	    Future<?> futureFirst = myThreadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("ִ�е�һ���߳�");
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println("��һ���̱߳��ж�~~~");
					}
				}
			}
		});
	    
	    // �ֶ���������ȡ��
	    // futureFirst.cancel(true);
	    
	    Thread.sleep(1000);
	    System.out.println("ִ����~~~�������ύ�ڶ����߳�~~~");
	    myThreadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("ִ�еڶ����̣߳���Ϊ��һ���߳��޷��������ڶ����߳�һֱ���ڶ����еȴ�");
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	    Thread.sleep(1000);
	    System.out.println("ִ����~~~�������ύ�������߳�~~~");
	    myThreadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("ִ�е������̣߳��̴߳ﵽ����߳��������Ҷ�������������ֱ�Ӿܾ�~~~");
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
}
