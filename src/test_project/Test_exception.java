package test_project;

public class Test_exception {
	public static void main(String[] args)
	{
		try {
			lopss();
		} catch (Exception e) {
			System.out.println("�սᣡ");
		}
	}
	
	public static void lopss() throws Exception
	{
		for(int i=0;i<10;i++)
		{
			System.out.println("����ѭ��qian��"+i+"�Σ�");
			equals();
			System.out.println("����ѭ��hou��"+i+"�Σ�");
		}
		System.out.println("������");
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
