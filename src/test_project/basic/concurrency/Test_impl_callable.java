package test_project.basic.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test_impl_callable implements Callable<String> {

	@Override
	public String call() throws Exception {
		return "Ö´ÐÐcall·½·¨";
	}

	public static void main(String args[]) throws InterruptedException, ExecutionException {

		FutureTask<String> future = new FutureTask<String>(new Test_impl_callable());
		Thread thread = new Thread(future);
		thread.start();
		future.get();
	}
	
}
