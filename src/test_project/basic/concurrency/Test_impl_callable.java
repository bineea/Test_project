package test_project.basic.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test_impl_callable implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("excute~~~~~~");
		throw new Exception("exception test~~~~~~");
//		return "ִ��call����";
	}

	public static void main(String args[]) throws InterruptedException {

		FutureTask<String> future = new FutureTask<String>(new Test_impl_callable());
		Thread thread = new Thread(future);
		thread.start();
		Thread.sleep(2000);
		try {
			String result = future.get();
			System.out.println("ִ��future.get()��������ȡ����ֵ��"+result);
		} catch (ExecutionException e) {
			System.out.println("ִ��future.get()�������������쳣");
			e.printStackTrace();
		}
	}
	
}
