package test_project.jvm.OOM;

public class Test_oom_runtimeConstantPool {

	//jdk1.7之后，intern()方法不会再复制实例，只是在常量池中记录首次出现的实例引用，因此intern()方法返回的引用和由StringBuilder创建的字符串实例是同一个！
	public static void main(String[] args)
	{
		String str1 = new StringBuilder().append("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder().append("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
}
