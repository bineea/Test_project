package test_project.exercise.leetcode;

import java.util.Arrays;

/**
 * ����һ���������� nums���� nums �еĵ�����ż��Ԫ���ƶ��������ǰ�棬�����������Ԫ�ء�
 * ��������������� ��һ���� ��Ϊ�𰸡�
 * 
 * ���룺nums = [3,1,2,4]
 * �����[2,4,3,1]
 * ���ͣ�[4,2,3,1]��[2,4,1,3] �� [4,2,1,3] Ҳ�ᱻ������ȷ�𰸡�
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
        
    	// �����±�
    	int oddIndex = nums.length -1;
    	// ż���±�
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
    	
    	// �����±�
    	int oddIndex = 0;
    	// ż���±�
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
