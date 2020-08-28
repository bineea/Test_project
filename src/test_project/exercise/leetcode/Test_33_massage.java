package test_project.exercise.leetcode;

public class Test_33_massage {

	 public int massage(int[] nums) {
		 int res = 0;
		 res = handleMassage(-2, nums, 0);
		 return res;
	 }
	 
	 public int handleMassage(int x, int[] nums, int count) {
		 if(x>=nums.length-1 || x+1 >= nums.length-1) {
			 return count;
		 }
		 int res1 = 0, res2 = 0;
		 if(x+2 < nums.length) {
			 res1 = handleMassage(x+2, nums, count + nums[x+2]);
		 }
		 if(x+3 < nums.length) {
			 res2 = handleMassage(x+3, nums, count + nums[x+3]);
		 }
		 return Math.max(res1, res2);
	 }
	 
	 public static void main(String[] args) {
		 Test_33_massage test = new Test_33_massage();
		 System.out.println(test.massage(new int[] {1,2,3,1}));
		 System.out.println(test.massage(new int[] {2,7,9,3,1}));
		 System.out.println(test.massage(new int[] {2,1,4,5,3,1,1,3}));
	 }
}
