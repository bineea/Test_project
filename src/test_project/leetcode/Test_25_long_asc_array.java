package test_project.leetcode;

/**
 * ����һ��������������飬�ҵ���������������еĳ��ȡ�
 * 
 * ʾ��:
 * ����: [10,9,2,5,3,7,101,18]
 * ���: 4 
 * ����: ��������������� [2,3,7,101]�����ĳ����� 4��
 * ˵��:
 *     ���ܻ��ж�������������е���ϣ���ֻ��Ҫ�����Ӧ�ĳ��ȼ��ɡ�
 *     ���㷨��ʱ�临�Ӷ�Ӧ��Ϊ O(n2) ��
 * ����: ���ܽ��㷨��ʱ�临�ӶȽ��͵� O(n log n) ��?
 * 
 * 
 * 
 * ����˼·��
 * ���� dp[i] Ϊ����ǰ i��Ԫ�أ��Ե� i�����ֽ�β������������еĳ��ȣ�ע�� nums[i]���뱻ѡȡ��
 * ���Ǵ�С�������dp[]�����ֵ���ڼ��� dp[i]֮ǰ�������Ѿ������dp[0...i-1]��ֵ����״̬ת�Ʒ���Ϊ��
 * dp[i]=max(dp[j])+1,����0<=j<i��num[j]<num[i]
 * ��������dp[0...i-1]��������������к����ټ�һ�� nums[i]��
 * ���� dp[j]���� nums[0��j]���� nums[j]��β������������У���������ܴ� dp[j]���״̬ת�ƹ�����
 * ��ô nums[i]��ȻҪ���� nums[j]�����ܽ� nums[i]���� nums[j]�������γɸ��������������С�
 * 
 * @author binee
 *
 */
public class Test_25_long_asc_array {
	
	public int lengthOfLIS_BL(int[] nums) {
		if(nums.length <= 0)
			return 0;
		if(nums.length == 1)
			return 1;
		int res = 0;
		for(int x=0; x<nums.length-1; x++) {
			res = Math.max(res, handleLengthOfLIS(nums, nums[x], x+1) + 1);
		}
		return res;
    }
	
	public int handleLengthOfLIS(int[] nums, int minVal, int start) {
		int res = 0;
		for(int i=start; i<nums.length; i++) {
			int count = 0;
			if(nums[i] > minVal) {
				count++;
				count += handleLengthOfLIS(nums, nums[i], i+1);
			}
			res = Math.max(res, count);
		}
		return res;
	}
	
	public int lengthOfLIS_FINAL_ONE(int[] nums) {
		if(nums.length <= 0)
			return 0;
		if(nums.length == 1)
			return 1;
		
		int res = 0;
		int[] dp = new int[nums.length];
		for(int x=0; x<nums.length; x++) {
			int temp = 1;
			for(int y=0; y<x; y++) {
				if(nums[y] < nums[x]) {
					temp = Math.max(temp, dp[y] + 1);
				}
			}
			dp[x] = temp;
			res = Math.max(res, dp[x]);
		}
		return res;
    }
	
	public int lengthOfLIS_FINAL_TWO(int[] nums) {
		int res = 0;
		
		
		
		return res;
    }

	
	public static void main(String[] args) {
		Test_25_long_asc_array test = new Test_25_long_asc_array();
		System.out.println(test.lengthOfLIS_BL(new int[] { 4, 10, 4, 3, 8, 9 }));
		System.out.println(test.lengthOfLIS_BL(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
		System.out.println(test.lengthOfLIS_BL(new int[] { 10, 9, 2, 5, 3, 4 }));
	}
}
