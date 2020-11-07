package test_project.jvm.OOM;

//�����ջ�ͱ��ط���ջ���
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
			System.out.println("ÿ��ջ֡ռ���ֽڴ�СΪ����ǰ����-XssΪ180k��ջ���Ϊ" + testStack.stackLength+"����180 * 1024 / " + testStack.stackLength );
		}
	}
}
