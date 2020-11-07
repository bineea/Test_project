package test_project.jvm.OOM;

import java.util.ArrayList;
import java.util.List;

//java¶ÑÒç³ö
//-verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError

public class Test_oom_heap {
	
	static class OOMObject
	{
		
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<OOMObject> list = new ArrayList<OOMObject>();
				while(true)
				{
					list.add(new OOMObject());
				}
			}
		});
		
		thread.start();
		thread.join();
	}

}
