package test_project;

public class Test_semiangle {
	
	public static String toDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
        	if(c[i] >= '\u4e00' && c[i] <= '\u9fa5'){
        		continue;
        	}
        	else if (c[i] == '\u3000') {
        		c[i] = ' ';
        	} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
        		c[i] = (char) (c[i] - 65248);
        	}
        }
	    String returnString = new String(c);
        return returnString;
	}
	public static void main(String[] args)
	{
		String num="�������������������������������������������Ǹ���ʡ�ļ���ҷ�������������ƽϴ����ݣ�����������";
		String xxx="�������Σ�һ�׻������£�������������";
		System.out.println(num);
		System.out.println(Test_semiangle.toDBC(num));
		System.out.println(Test_semiangle.toDBC(xxx));
		num="370783198509181377";
		System.out.println(Test_semiangle.toDBC(num));
	}
}
