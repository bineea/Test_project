package test_project;

public class Test_number {
	public static void main(String[] args)
	{
		String str = "";
		Double f = Double.valueOf(str) * Double.valueOf(100);
		System.out.println(f);
		Long l = Long.valueOf(f.longValue());
		System.out.println(l);
	}
}
