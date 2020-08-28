package test_project.exercise.leetcode;

import java.util.Stack;

/**
 * ����һ��ֻ���� '(' �� ')' ���ַ������ҳ���İ�����Ч���ŵ��Ӵ��ĳ��ȡ�
 * 
 * 
 * 
 * ���磺 
 * ����: ")()())"
 * ���: 4
 * ����: ���Ч�����Ӵ�Ϊ "()()"
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
