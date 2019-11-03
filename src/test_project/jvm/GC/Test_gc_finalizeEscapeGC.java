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
		
		//对象第一次拯救自己
		SAVE_HOOK = null;
		System.gc();
		
		//finalize方法优先级很低，所以此处暂定0.5秒来等待他执行
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("no i'm dead :(");
		
		//下面这段代码与上面完全一致，但是这次将无法进行自救
		SAVE_HOOK = null;
		System.gc();
		
		//finalize方法优先级很低，所以此处暂定0.5秒来等待他执行
		Thread.sleep(500);
		if(SAVE_HOOK != null)
			SAVE_HOOK.isAlive();
		else
			System.out.println("no i'm dead :(");
	}
}
