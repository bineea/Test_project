package test_project.exercise.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

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
