package test_project.jvm.OOM;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果用户线程发生OOM，并且存在其他用户线程，则不影响其他用户线程执行
 * @author binee
 *
 */
public class Test_oom_heap_normal_thread {
	
	public static void main(String[] args) throws Exception {
		Thread thread_1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<byte[]> list = new ArrayList<byte[]>();
				int i = 0;
				while(true)
				{
					System.out.println(Thread.currentThread().getName()+ "running..." + (++i));
					list.add(new byte[1024 * 1024 * 1]);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "thread_1");
		
		thread_1.start();
		
		Thread thread_2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 0;
				while(true)
				{
					System.out.println(Thread.currentThread().getName()+ "running..." + (++i));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "thread_2");
		
		thread_2.start();
	}

}
