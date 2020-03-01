package test_project.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
  * ������ջʵ��һ�����С����е��������£���ʵ�������������� appendTail �� deleteHead ���ֱ�����ڶ���β�������������ڶ���ͷ��ɾ�������Ĺ��ܡ�
 * (��������û��Ԫ�أ�deleteHead �������� -1 )
 * @author binee
 *
 */
public class Test_7_stack2queue {
	
    static class CQueue {
    	
    	Stack<Integer> val = new Stack<Integer>();
    	Stack<Integer> bak = new Stack<Integer>();

    	LinkedList<Integer> test;
        public CQueue() {

        }
        
        public void appendTail(int value) {
    		val.push(value);
        }
        
        public int deleteHead() {
        	int result = -1;
        	if(!bak.isEmpty())
        		result = bak.pop();
        	else if(!val.isEmpty() && bak.isEmpty()) {
        		while(!val.isEmpty()) {
        			bak.push(val.pop());
        		}
        		result = bak.pop();
        	}
        	return result;
        }
    }
    
    public static void main(String[] args) {
    	
    }
}
