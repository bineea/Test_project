package test_project.exercise.leetcode;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 
 * 
 * 
 * 例如： 
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * @author bineea
 *
 */
public class Test_31_longest_valid_parentheses {

	public int longestValidParentheses_BL(String s) {
		int res = 0;
		
		for(int i=0; i<s.length(); i++) {
			for(int j=i+2; j<=s.length(); j+=2) {
				if(isValid_BL(s.substring(i,j))) {
					res = Math.max(res, j-i);
				}
			}
		}
		
		return res;
    }
	
	public boolean isValid_BL(String s) {
		
		Stack<Character> stack = new Stack<Character>();
		char[] toHandleChar = s.toCharArray();
		for(int i=0; i<toHandleChar.length; i++) {
			if(toHandleChar[i] == '(') {
				stack.push(toHandleChar[i]);
			} else if(toHandleChar[i] == ')' && !stack.isEmpty()) {
				stack.pop();
			} else {
				return false;
			}
		}
		
		return stack.empty();
	}
	
	public static void main(String[] args) {
		Test_31_longest_valid_parentheses test = new Test_31_longest_valid_parentheses();
		System.out.println(test.longestValidParentheses_BL(")()())"));
		System.out.println(test.longestValidParentheses_BL("()(()"));
		System.out.println(test.longestValidParentheses_BL("(()"));
	}
}
