package test_project.jvm.GC;

public class Test_gc_finalizeEscapeGC {

	private static Test_gc_finalizeEscapeGC SAVE_HOOK = null;
	
	private void isAlive()
	{
		System.out.println("yes, i'm still alive :)");
	}
	
	@Override
	public void finalize() throws Throwable
	{
		super.finalize();
		System.out.println("finalize method executed!");
		Test_gc_finalizeEscapeGC.SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Exception
	{
		SAVE_HOOK = new Test_gc_finalizeEscapeGC();
		
		//�����һ�������Լ�
		SAVE_HOOK = null;
		System.gc();
		
		//finalize�������ȼ��ܵͣ����Դ˴��ݶ�0.5�����ȴ���ִ��
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("no i'm dead :(");
		
		//������δ�����������ȫһ�£�������ν��޷������Ծ�
		SAVE_HOOK = null;
		System.gc();
		
		//finalize�������ȼ��ܵͣ����Դ˴��ݶ�0.5�����ȴ���ִ��
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("no i'm dead :(");
	}
}
