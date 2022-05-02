package test_project.exercise.leetcode;

import java.util.Arrays;

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
		String res1 = Arrays.toString(test.sortArrayByParity_1(new int[] {3,1,2,4}));
		System.out.println(res1);
		String res2 = Arrays.toString(test.sortArrayByParity_1(new int[] {0,1}));
		System.out.println(res2);
	}
	
	/**
	 * @param nums
	 * @return
	 */
    public int[] sortArrayByParity_1(int[] nums) {
        int[] resNums = new int[nums.length];
        
    	// 奇数下标
    	int oddIndex = nums.length -1;
    	// 偶数下标
    	int evenIndex = 0;
        
        for(int i=0;i<nums.length;i++){
            if(nums[i] % 2 == 0){
            	resNums[evenIndex] = nums[i];
            	evenIndex++;
            }else{
            	resNums[oddIndex] = nums[i];
            	oddIndex--;           }
        }

        return resNums;
    }
    
    /**
     * 
     * @param nums
     * @return
     */
    public int[] sortArrayByParity_2(int[] nums) {
    	
    	// 奇数下标
    	int oddIndex = 0;
    	// 偶数下标
    	int evenIndex = nums.length -1;
    	for(int i=nums.length - 1; i>=0; i--){
    		if(nums[i] % 2 == 0){
    			evenIndex = i;
            } else {
            	continue;
            }
    		
    		for (int x=oddIndex; x<evenIndex; x++) {
    			if(nums[x] % 2 != 0) {
    				oddIndex = x;
    				break;
    			}
    		}
    		int temp = nums[i];
    		nums[i] = nums[oddIndex];
    		nums[oddIndex] = temp;
    	}
    	return nums;
    }
    
    
}
