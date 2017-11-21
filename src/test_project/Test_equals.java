package test_project;

public class Test_equals {
	public static void main(String args[]){
		Test_equals t= new Test_equals();
		try {
			t.second();
		} catch (Exception e) {
			System.out.println("执行4");
		}
		System.out.println("执行3");
	}
	
	public void second()
	{
		Test_equals te= new Test_equals();
		try {
			te.first();
		} catch (Exception e) {
			System.out.println("执行5");
		}
		System.out.println("执行2");
	}
	
	public void first() throws Exception
	{
		String s1=null;
		String s2="display";
		if(!s2.equals(s1)){
			System.out.println("YES!!");
		}
//		if(s1.equals(s2)){
//			System.out.println("OK!!");
//		}
		System.out.println("执行1");
	}
}
