package test_project;

public class Test_add {
	public static void main(String args[]){
		for(int a=0,b=0,c=0;c<10;c++){
			System.out.println(a++ + "-----" + ++b);
			System.out.println("^^^^^^^^^^^^^");
		}
		Test_add dd = new Test_add();
		int x=0;
		Integer y = new Integer(0);
		dd.add(1,y);
		System.out.println("x="+x);
		System.out.println("y="+y);
	}
	
	public Integer add(int x,int y)
	{
		y=y+x;
		return y;
	}
}
//a++ 是先用a再加；++b 是先加再用b