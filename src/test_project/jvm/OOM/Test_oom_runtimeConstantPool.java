package test_project.jvm.OOM;

public class Test_oom_runtimeConstantPool {

	//jdk1.7֮��intern()���������ٸ���ʵ����ֻ���ڳ������м�¼�״γ��ֵ�ʵ�����ã����intern()�������ص����ú���StringBuilder�������ַ���ʵ����ͬһ����
	public static void main(String[] args)
	{
		String str1 = new StringBuilder().append("�����").append("���").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder().append("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
}
