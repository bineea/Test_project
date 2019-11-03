package test_project.jvm.OOM;

public class Test_oom_stack_single_thread {
	
	private int stackLength = -1;
	
	public void stackLeak()
	{
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args)
	{
		Test_oom_stack_single_thread testStack = new Test_oom_stack_single_thread();
		try
		{
			testStack.stackLeak();
		}
		catch(Throwable e)
		{
			System.out.println("stack length :" + testStack.stackLength);
			e.printStackTrace();
		}
	}
}
