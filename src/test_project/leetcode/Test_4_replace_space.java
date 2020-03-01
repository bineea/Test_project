package test_project.leetcode;

/**
  * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
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
