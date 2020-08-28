package test_project.exercise.leetcode;

import java.util.Stack;

/**
  * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * @author binee
 *
 */
public class Test_5_reverse_order_list {
	public int[] reversePrint(ListNode head) {
		if(head != null) {
			Stack<Integer> resultStack = handleListNote2Stack(head, new Stack<Integer>());
			int stactSize = resultStack.size();
			int[] result = new int[stactSize];
			for(int i=0; i<stactSize; i++) {
				result[i] = resultStack.pop();
			}
			return result;
		}
		return new int[] {};
    }
	
	public Stack<Integer> handleListNote2Stack (ListNode head, Stack<Integer> stack) {
		stack.push(head.val);
		if(head.next != null) {
			handleListNote2Stack(head.next, stack);
		}
		return stack;
	}
	
	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args) {
		Test_5_reverse_order_list test = new Test_5_reverse_order_list();
		test.reversePrint(test.new ListNode(0));
	}
}
