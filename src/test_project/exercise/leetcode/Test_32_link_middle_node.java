package test_project.exercise.leetcode;

/**
 * ����һ������ͷ��� head �ķǿյ���������������м��㡣
 * ����������м��㣬�򷵻صڶ����м��㡣
 * 
 * 
 * @author bineea
 *
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Test_32_link_middle_node {

    public ListNode middleNode(ListNode head) {
    	if(head == null) return null;
    	int count = 1;
    	ListNode res = head;
    	while(head.next != null) {
    		count+=1;
    		if(count % 2 == 0) {
    			res = res.next;
    		}
    		head = head.next;
    	}
    	return res;
    }
    
    class ListNode {
    	int val;
    	ListNode next;
    	ListNode(int x) { val = x; }
    }
}
