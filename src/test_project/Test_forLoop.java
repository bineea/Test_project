package test_project;

public class Test_forLoop {
	public static void main(String[] args)
	{
		int s = 7;
		String str = "��ʼ�ַ���";
		int m=1;
		for(int i=0;i<s;i++)
		{
			if(m%2==0)
			{
				str = "ż���ַ���"+m;
			}
			else if(m%2 != 0 && m == s)
			{
				str = "����ַ���"+m;
			}
			System.out.println(str);
			m++;
		}
		System.out.println(1/5*5+1);
	}
}
