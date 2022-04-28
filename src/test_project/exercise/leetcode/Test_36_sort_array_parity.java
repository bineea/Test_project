package test_project.exercise.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 返回满足此条件的 任一数组 作为答案。
 * 
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * 
 * @author bineea
 *
 */
public class Test_36_sort_array_parity {

	public static void main(String[] args) {
		Test_36_sort_array_parity test = new Test_36_sort_array_parity();
		String res = Arrays.toString(test.sortArrayByParity(new int[] {3,1,2,4}));
		System.out.println(res);
	}
	
    public int[] sortArrayByParity(int[] nums) {
        LinkedList<Integer> link = new LinkedList<Integer>();
        for(int i=0;i<nums.length;i++){
            if(nums[i] % 2 == 0){
                link.push(nums[i]);
            }else{
                link.offer(nums[i]);
            }
        }
        Integer[] temp = link.toArray(new Integer[nums.length]);
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++){
        	res[i] = temp[i];
        }
        return res;
    }
}
