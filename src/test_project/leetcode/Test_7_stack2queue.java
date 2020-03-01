package test_project.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
  * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
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
