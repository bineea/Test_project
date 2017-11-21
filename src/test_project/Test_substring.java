package test_project;

public class Test_substring {
	public static void main(String args[]){
		String str1="123456789wertyui78";
		String str2="1234567890";
		String xxx1=str1.substring(8, 17);
		String xxx2=str2.substring(5,10);
		String xxx3=str2.substring(1);
		System.out.println(str1+"\n"+xxx1+"\n"+str2+"\n"+xxx2+"\n"+xxx3);
		
		
		String str3="123456789";
		String xxx4=str3.substring(0, 8)+"-"+str3.substring(8);
		System.out.println(xxx4);
		
		String xxx5=str1.substring(8, 16) + "-" + str1.substring(16, 17);
		System.out.println(xxx5);
		
	}
}
