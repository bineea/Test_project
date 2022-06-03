package test_project.basic.concurrency;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
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
				long l = 0L;
				while(true) {
					l++;
					if (l % 100000000 == 0) {
						System.out.println("��һ���߳�ִ����~~~");
					}
					
					// runnable �޷�����ִ���жϣ���Ҫ�߼�У���жϱ�ǣ��������ж�
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("��һ���̱߳��ж�~~~");
						break;
					}
//					System.out.println("��һ���߳�ִ����~~~");
//					try {
//						System.out.println("��һ���߳�ִ����~~~");
						// Thread.sleep(1000) �����жϱ�־�����ж��쳣��
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//						System.out.println("��һ���̱߳��ж�~~~");
//						throw new RuntimeException(e);
//					}
				}
			}
		});
	    
	    // �ֶ���������ȡ��
	    // futureFirst.cancel(true);
	    
	    Thread.sleep(1000);
	    System.out.println("ִ����~~~�������ύ�ڶ����߳�~~~");
	    Future<?> futureSecond = myThreadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("ִ�еڶ����̣߳���Ϊ��һ���߳��޷��������ڶ����߳�һֱ���ڶ����еȴ�");
				while(true) {
					try {
						System.out.println("�ڶ����߳�ִ����~~~");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("�ڶ����̱߳��ж�~~~");
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		});
	    
	    // �ֶ���������ȡ�����������ڵڶ����߳�һֱ���ڶ����еȴ�����Ȼ����Ϊȡ�������ǲ�û���Ƴ��������
	    boolean secondResult = futureSecond.cancel(true);
	    System.out.println("�жϵڶ����߳̽����"+secondResult);
	    
	    Thread.sleep(1000);
	    System.out.println("ִ����~~~�������ύ�������߳�1~~~");
	    
	    // ��Ҫȷ���߳��Ƿ񲶻��ж��쳣������̲߳����ж��쳣�����޷��ж��̣߳�����
	    boolean firstResult = futureFirst.cancel(true);
	    System.out.println("�жϵ�һ���߳̽����"+firstResult);
	    
	    Thread.sleep(1000);
	    System.out.println("ִ����~~~�������ύ�������߳�2~~~");

	    try {
	    	Future<?> futureThird = myThreadPoolExecutor.submit(new Runnable() {
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
		    System.out.println("�������߳��Ƿ�ȡ������ִ�У�"+futureThird.isCancelled());
	    } catch (RejectedExecutionException ex) {
	    	ex.printStackTrace();
	    	System.out.println("����ܾ��쳣~~~");
	    }
	    Thread.sleep(1000);
	    System.out.println("���߳̽���~~~");
	}
	
}
