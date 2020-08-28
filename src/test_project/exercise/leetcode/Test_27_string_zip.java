package test_project.exercise.leetcode;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
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
