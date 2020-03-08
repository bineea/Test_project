package test_project.leetcode;

import java.util.LinkedList;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * 
 * 解题思路：
 * 1.排序
 * 2.维护一个单调递减的辅助队列！！！
 */


/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */

public class Test_18_max_val_in_queue {
	
	private LinkedList<Integer> queue;
	private LinkedList<Integer> maxValue;
	
	public Test_18_max_val_in_queue() {
		this.queue = new LinkedList<Integer>();
		this.maxValue = new LinkedList<Integer>();
    }
    
    public int max_value() {
    	int res = -1;
    	if(!maxValue.isEmpty()) {
    		res = maxValue.getFirst();
    	}
    	return res;
    }
    
    public void push_back(int value) {
    	queue.addLast(value);
    	while(!maxValue.isEmpty()) {
    		if(maxValue.getLast() >= value) {
    			break;
    		}
    		maxValue.removeLast();
    	}
    	maxValue.addLast(value);
    }
    
    public int pop_front() {
    	if(queue.isEmpty()) {
    		return -1;
    	}
    	else {
//    		使用Integer比较是否相等，需使用equals方法！！！
//    		Integer front = queue.getFirst();
//    		if(maxValue.getFirst().equals(front)) {
//    			maxValue.removeFirst();
//    		}
    		int front = queue.getFirst();
    		if(maxValue.getFirst() == front) {
    			maxValue.removeFirst();
    		}
    		queue.removeFirst();
    		return front;
    	}
    }
    

    public static void main(String[] args) {
    	Test_18_max_val_in_queue test = new Test_18_max_val_in_queue();
    	
    	test.max_value();
    	test.pop_front();
    	test.max_value();
    	test.push_back(46);
    	test.max_value();
    	test.pop_front();
    	test.max_value();
    	test.pop_front();
    }
}
