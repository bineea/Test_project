package test_project.exercise.leetcode;

/**
 * �ַ���ѹ���������ַ��ظ����ֵĴ�������дһ�ַ�����ʵ�ֻ������ַ���ѹ�����ܡ����磬�ַ���aabcccccaaa���Ϊa2b1c5a3��
 * ����ѹ��������ַ���û�б�̣��򷵻�ԭ�ȵ��ַ���������Լ����ַ�����ֻ������СдӢ����ĸ��a��z����
 * 
 * 
 * @author binee
 *
 */
public class Test_27_string_zip {
	
	public String compressString_BL(String S) {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for(char x : S.toCharArray()) {
			if(builder.length() <= 0) {
				builder.append(x);
			} else if(builder.length() > 0 && x != builder.charAt(builder.length()-1)) {
				builder.append(count);
				builder.append(x);
				count = 0;
			}
			count++;
		}
		builder.append(count);
		return builder.length() > S.length() ? S : builder.toString();
    }
	
	public static void main(String[] args) {
		Test_27_string_zip test = new Test_27_string_zip();
		System.out.println(test.compressString_BL("aabcccccaaa"));
		System.out.println(test.compressString_BL("abbccd"));
	}

}
