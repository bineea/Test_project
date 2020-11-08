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
	
	/**
	 * ����һ���ɡ�1������0����ɵ��ַ��������ַ����С�1������0���ַ������s������һһ��Ӧ
	 * �ַ����С�1����ʾ��Ӧλ�õĲ���s������Ϊ��Ч����
	 * �ַ����С�0����ʾ��Ӧλ�õĲ���s������Ϊ��Ч����
	 * 
	 * ͨ��ջ��ɲ���s�����ŵ�ƥ�䣬ƥ������и����ַ�����Ӧλ�õ�ֵΪ��1�����ߡ�0��
	 * 
	 * ����ַ����ԡ�0��Ϊ�ָ����ָ������С�1��������Ⱦ�����Ч���ŵ������
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
