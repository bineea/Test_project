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
				System.out.println("执行第一个线程");
				long l = 0L;
				while(true) {
					l++;
					if (l % 100000000 == 0) {
						System.out.println("第一个线程执行中~~~");
					}
					
					// runnable 无法主动执行中断，需要逻辑校验中断标记，并处理中断
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("第一个线程被中断~~~");
						break;
					}
//					System.out.println("第一个线程执行中~~~");
//					try {
//						System.out.println("第一个线程执行中~~~");
						// Thread.sleep(1000) 会检测中断标志触发中断异常！
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//						System.out.println("第一个线程被中断~~~");
//						throw new RuntimeException(e);
//					}
				}
			}
		});
	    
	    // 手动操作任务取消
	    // futureFirst.cancel(true);
	    
	    Thread.sleep(1000);
	    System.out.println("执行中~~~，即将提交第二个线程~~~");
	    Future<?> futureSecond = myThreadPoolExecutor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("执行第二个线程，因为第一个线程无法结束，第二个线程一直处于队列中等待");
				while(true) {
					try {
						System.out.println("第二个线程执行中~~~");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("第二个线程被中断~~~");
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		});
	    
	    // 手动操作任务取消，但是由于第二个线程一直处于队列中等待，虽然设置为取消，但是并没有移出任务队列
	    boolean secondResult = futureSecond.cancel(true);
	    System.out.println("中断第二个线程结果："+secondResult);
	    
	    Thread.sleep(1000);
	    System.out.println("执行中~~~，即将提交第三个线程1~~~");
	    
	    // 需要确认线程是否捕获中断异常，如果线程捕获中断异常，则无法中断线程！！！
	    boolean firstResult = futureFirst.cancel(true);
	    System.out.println("中断第一个线程结果："+firstResult);
	    
	    Thread.sleep(1000);
	    System.out.println("执行中~~~，即将提交第三个线程2~~~");

	    try {
	    	Future<?> futureThird = myThreadPoolExecutor.submit(new Runnable() {
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
		    System.out.println("第三个线程是否被取消任务执行："+futureThird.isCancelled());
	    } catch (RejectedExecutionException ex) {
	    	ex.printStackTrace();
	    	System.out.println("捕获拒绝异常~~~");
	    }
	    Thread.sleep(1000);
	    System.out.println("主线程结束~~~");
	}
	
}
