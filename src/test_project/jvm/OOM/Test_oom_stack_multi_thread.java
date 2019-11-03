package test_project.jvm.OOM;

public class Test_oom_stack_multi_thread {
	
	private void dontStop()
	{
		while(true)
		{
			
		}
	}
	
	public void stackLeakByThread()
	{
		while(true)
		{
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run()
				{
					dontStop();
				}
			});
			thread.start();
		}

	}
	
	public static void main(String[] args)
	{
		Test_oom_stack_multi_thread testStack = new Test_oom_stack_multi_thread();
		testStack.stackLeakByThread();
	} 

}
