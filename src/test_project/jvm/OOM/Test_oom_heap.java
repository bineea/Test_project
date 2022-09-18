package test_project.jvm.OOM;

import java.util.ArrayList;
import java.util.List;

//java¶ÑÒç³ö
//-verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError

public class Test_oom_heap {
	
	public static void main(String[] args) throws InterruptedException
	{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<byte[]> list = new ArrayList<byte[]>();
				while(true)
				{
					System.out.println(Thread.currentThread().getName());
					list.add(new byte[1024 * 1024 * 1]);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		thread.start();
		thread.join();
	}

}