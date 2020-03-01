package test_project.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
  * ����һ���������� nums ��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ���� ���� ���������������ǵ������±ꡣ
  * ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ��㲻���ظ��������������ͬ����Ԫ�ء�
 * @author binee
 */
public class Test_1_two_int_add {

	public int[] twoSum_BL(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
            for(int x=i+1; x<nums.length; x++) {
                if((nums[i] + nums[x]) == target)
                    return new int[] {i,x};
            }
        }
        return null;
    }
	
	public int[] twoSum_FINAL(int[] nums, int target) {
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int i=0; i<nums.length; i++) {
        	if(m.get(nums[i]) != null && i != m.get(nums[i]))
        		return new int[] {i, m.get(nums[i])};
        	m.put(target - nums[i], i);
        }
        return null;
    }
	
	public static void main(String[] args) {
		Test_1_two_int_add test = new Test_1_two_int_add();
		int [] nums = {2, 7, 11, 15};
		int target = 9;
		int [] result_BL = test.twoSum_BL(nums, target);
		int [] result_FINAL = test.twoSum_FINAL(nums, target);
		System.out.println(result_BL[0] + ";" + result_BL[1]);
		System.out.println(result_FINAL[0] + ";" + result_FINAL[1]);
	}
}
