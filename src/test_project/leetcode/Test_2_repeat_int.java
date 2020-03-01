package test_project.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
  * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
  * 请找出数组中任意一个重复的数字。
 * @author binee
 */
public class Test_2_repeat_int {

	public int findRepeatNumber(int[] nums) {
		Map<Integer, Integer> repeatNums = new HashMap<Integer, Integer>();
		for(int i=0; i<nums.length; i++) {
			if(repeatNums.get(nums[i]) != null && repeatNums.get(nums[i]) >= 1)
				return nums[i];
			else if(repeatNums.get(nums[i]) == null)
				repeatNums.put(nums[i], 1);
		}
		return 0;
    }
	
	public static void main(String[] args) {
		Test_2_repeat_int test = new Test_2_repeat_int();
		int[] intArray = {2, 3, 1, 0, 2, 5, 3};
		int result = test.findRepeatNumber(intArray);
		System.out.println(result);
	}
}
