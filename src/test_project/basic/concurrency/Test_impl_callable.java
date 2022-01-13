package test_project.basic.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test_impl_callable implements Callable<String> {

	@Override
	public String call() throws Exception {
		throw new Exception("exception test~~~~~~");
		//return "执行call方法";
	}

	public static void main(String args[]) throws InterruptedException {

		FutureTask<String> future = new FutureTask<String>(new Test_impl_callable());
		Thread thread = new Thread(future);
		thread.start();
		Thread.sleep(2000);
		try {
			String result = future.get();
			System.out.println("执行future.get()方法，获取返回值："+result+"，并捕获异常");
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}
