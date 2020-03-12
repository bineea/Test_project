package test_project.leetcode;

/**
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 * 
 * @author binee
 *
 */
public class Test_23_common_string {
	
	public String gcdOfStrings_BL(String str1, String str2) {
		String result = "";
		if(str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1)
			return result;
		
		if(str1.charAt(0) != str2.charAt(0) || str1.charAt(str1.length()-1) != str2.charAt(str2.length()-1))
			return result;
		
		String minStr = str1, maxStr = str2;
		if(str1.length() > str2.length()) {
			maxStr = str1;
			minStr = str2;
		}
		
		String temp = "";
		for(int i=1; i<= maxStr.length() / minStr.length(); i++) {
			temp += minStr;
		}
		if(temp.equals(maxStr)) {
			return minStr;
		}
		
		int min_multiple = 0;
		int max_multiple = 0;
		String signStr = String.valueOf(minStr.charAt(minStr.length()-1)) + String.valueOf(minStr.charAt(0));
		int index = minStr.length() / 2;
		while(index >= 0) {
			String sub = minStr.substring(0, index+1);
			index = sub.lastIndexOf(signStr);
			if(index >= 0 && minStr.length() % (index+1) == 0 && maxStr.length() % (index+1) == 0) {
				String min_temp = "", max_temp = "";
				min_multiple = minStr.length() / (index+1);
				max_multiple = maxStr.length() / (index+1);
				result = minStr.substring(0, index+1);
				for(int i=1; i<=min_multiple; i++) {
					min_temp += result;
				}
				for(int i=1; i<=max_multiple; i++) {
					max_temp += result;
				}
				if(min_temp.equals(minStr) && max_temp.equals(maxStr))
					break;
			}
		}
		
		return result;
	}
	
	public static void main(String[]  args) {
		Test_23_common_string test = new Test_23_common_string();
		System.out.println(test.gcdOfStrings_BL("ABABAB", "ABAB"));
		System.out.println(test.gcdOfStrings_BL("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX"));
	}

}
