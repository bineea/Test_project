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
	
	/**
	 * 创建一个由“1”、“0”组成的字符串，该字符串中“1”、“0”字符与参数s中括号一一对应
	 * 字符串中“1”表示对应位置的参数s的括号为有效括号
	 * 字符串中“0”表示对应位置的参数s的括号为无效括号
	 * 
	 * 通过栈完成参数s中括号的匹配，匹配过程中更新字符串对应位置的值为“1”或者“0”
	 * 
	 * 最后将字符串以“0”为分隔符分隔，其中“1”的最长长度就是有效括号的最长长度
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses_sample(String s) {
		char[] c = s.toCharArray();
		int result = 0;
		StringBuilder build = new StringBuilder();
		
		Stack<Node> resultStack = new Stack<Node>();
		
		for(int i=0; i<c.length; i++) {
			build.append(0);
			if(c[i] != '(' && !resultStack.isEmpty()) {
				Node node = resultStack.peek();
				if(node.getValue() == '(' ) {
					build.setCharAt(i, '1');
					build.setCharAt(node.getIndex(), '1');
					resultStack.pop();
					continue;
				}
			} 
			resultStack.push(new Node(i, c[i]));
		}
		
		String[] strArray = build.toString().split("0");
		for(int x=0; x<strArray.length; x++) {
			result = Math.max(result, strArray[x].length());
		}
		return result >= 2 ? result : 0;
    }
	
	class Node {
		private int index;
		private char value;
		
		public Node(int index, char value) {
			this.index = index;
			this.value = value;
		}
		
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public char getValue() {
			return value;
		}
		public void setValue(char value) {
			this.value = value;
		}
		
	}
	
	public static void main(String[] args) {
		Test_31_longest_valid_parentheses test = new Test_31_longest_valid_parentheses();
		System.out.println(test.longestValidParentheses_sample(")()())"));//4
		System.out.println(test.longestValidParentheses_sample("()(()"));//2
		System.out.println(test.longestValidParentheses_sample("(()"));//2
		System.out.println(test.longestValidParentheses_sample("()(())"));//6
		
	}
}
