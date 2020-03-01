package test_project.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
  * ��һ������Ϊ n ������ nums ����������ֶ��� 0��n-1 �ķ�Χ�ڡ�������ĳЩ�������ظ��ģ�����֪���м��������ظ��ˣ�Ҳ��֪��ÿ�������ظ��˼��Ρ�
  * ���ҳ�����������һ���ظ������֡�
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
