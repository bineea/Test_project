package test_project.basic.concurrency;

import java.util.concurrent.Executors;

public class Test_threadPoolExecutor {
	
	public static void main(String[] args)
	{
		Executors.newSingleThreadExecutor();
		Executors.newFixedThreadPool(10);
		Executors.newScheduledThreadPool(10);
		Executors.newCachedThreadPool();
	}
	
}
