package test_project;

public class Test_replace {
	public static void main(String args[]){
		Test_replace t = new Test_replace();
		String s = "°®ÎÒÖĞ»ª";
		System.out.println(t.toreplace(s));
	}
	
	public String toreplace(String str){
		String str1 = str.substring(1, str.length()-1);
		str = str.substring(0,1) + str1.replaceAll(".", "*") + str.substring(str.length()-1);
		return str;
	}
}