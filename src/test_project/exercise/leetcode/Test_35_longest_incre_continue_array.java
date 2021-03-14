package test_project.exercise.leetcode;

import java.util.Stack;

/**
 * 最长连续递增子序列
 * @author binee
 *
 */
public class Test_35_longest_incre_continue_array {

	public static void main(String[] args) {
		Test_35_longest_incre_continue_array test = new Test_35_longest_incre_continue_array();
		System.out.println(test.findLengthOfLCIS_BL(new int[] {1,3,5,4,7}));
		System.out.println(test.findLengthOfLCIS_BL(new int[] {2,2,2,2,2}));
	}
	
	public int findLengthOfLCIS_BL(int[] nums) {
		
		int result = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<nums.length; i++) {
			if(stack.empty() || stack.lastElement() < nums[i]) {
				stack.add(nums[i]);
				result = Math.max(result, stack.size());
			} else {
				stack.clear();
				stack.add(nums[i]);
			}
		}
		
		return result;
	}
}
