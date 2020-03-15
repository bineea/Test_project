package test_project.leetcode;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4 
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *     可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 *     你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * 
 * 
 * 
 * 解题思路：
 * 定义 dp[i] 为考虑前 i个元素，以第 i个数字结尾的最长上升子序列的长度，注意 nums[i]必须被选取。
 * 我们从小到大计算dp[]数组的值，在计算 dp[i]之前，我们已经计算出dp[0...i-1]的值，则状态转移方程为：
 * dp[i]=max(dp[j])+1,其中0<=j<i且num[j]<num[i]
 * 即考虑往dp[0...i-1]中最长的上升子序列后面再加一个 nums[i]。
 * 由于 dp[j]代表 nums[0…j]中以 nums[j]结尾的最长上升子序列，所以如果能从 dp[j]这个状态转移过来，
 * 那么 nums[i]必然要大于 nums[j]，才能将 nums[i]放在 nums[j]后面以形成更长的上升子序列。
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
