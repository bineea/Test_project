package test_project.leetcode;

import java.util.LinkedList;

/**
 * �붨��һ�����в�ʵ�ֺ��� max_value �õ�����������ֵ��Ҫ����max_value��push_back �� pop_front ��ʱ�临�Ӷȶ���O(1)��
 * ������Ϊ�գ�pop_front �� max_value ��Ҫ���� -1
 * 
 * ����˼·��
 * 1.����
 * 2.ά��һ�������ݼ��ĸ������У�����
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
//    		ʹ��Integer�Ƚ��Ƿ���ȣ���ʹ��equals����������
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
