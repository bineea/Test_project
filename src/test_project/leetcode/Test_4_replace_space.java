package test_project.leetcode;

/**
  * ��ʵ��һ�����������ַ��� s �е�ÿ���ո��滻��"%20"��
 * @author binee
 *
 */
public class Test_4_replace_space {

	public String replaceSpace(String s) {
		return s.replace(" ", "%20");
    }
	
	public static void main(String[] args) {
		Test_4_replace_space test = new Test_4_replace_space();
		System.out.println(test.replaceSpace("asdf1 2 31 2      003   "));
	}
}
