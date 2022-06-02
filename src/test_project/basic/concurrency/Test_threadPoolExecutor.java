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
				System.out.println("执行第一个线程");
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println("第一个线程被中断~~~");
					}
				}
			}
		});
	    
	    // 手动操作任务取消
	    // futureFirst.cancel(true);
	    
	    Thread.sleep(1000);
	    System.out.println("执行中~~~，即将提交第二个线程~~~");
	    myThreadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("执行第二个线程，因为第一个线程无法结束，第二个线程一直处于队列中等待");
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
	    System.out.println("执行中~~~，即将提交第三个线程~~~");
	    myThreadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("执行第三个线程，线程达到最大线程数，并且队列已满，所以直接拒绝~~~");
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
