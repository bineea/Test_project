package test_project;

public class Test_exception {
	public static void main(String[] args)
	{
		try {
			lopss();
		} catch (Exception e) {
			System.out.println("终结！");
		}
	}
	
	public static void lopss() throws Exception
	{
		for(int i=0;i<10;i++)
		{
			System.out.println("正在循环qian第"+i+"次！");
			equals();
			System.out.println("正在循环hou第"+i+"次！");
		}
		System.out.println("结束！");
	}
	
	public static void equals() throws Exception
	{
		String s1=null;
		String s2="display";
		if(!s2.equals(s1)){
			System.out.println("YES!!");
		}
		if(s1.equals(s2)){
			System.out.println("OK!!");
		}
	}
}
