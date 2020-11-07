package test_project.jvm.OOM;

//虚拟机栈和本地方法栈溢出
//-verbose:gc -Xss180k
//The Java thread stack size specified is too small. Specify at least 180k

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
			e.printStackTrace();
			System.out.println("stack length :" + testStack.stackLength);
			System.out.println("每个栈帧占用字节大小为：当前配置-Xss为180k，栈深度为" + testStack.stackLength+"，即180 * 1024 / " + testStack.stackLength );
		}
	}
}
