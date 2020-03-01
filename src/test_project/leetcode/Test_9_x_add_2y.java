package test_project.leetcode;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 解题思路：
 * 假设跳上n级台阶共有f(n)种跳法。在所有跳法中，青蛙的最后一步只有两种情况：跳上1级台阶或是跳上2级台阶；
 * 
 * 当为1级台阶时，剩余n-1级台阶，此情况共有f(n-1)种跳法；
 * 当为2级台阶时，剩余n-2级台阶，此情况共有f(n-2)种跳法；
 * 
 * f(n)为以上两种情况之和，所以f(n) = f(n-1) + f(n-2)，以上递推性质为斐波那契数列！！！
 * 
 * 因为n=1时，f(1)=1；n=2时，f(n)=2；所以f(0) = f(2) - f(1) = 1！！！
 * 
 * @author binee
 *
 */
public class Test_9_x_add_2y {

	public int numWays(int n) {
		int zero = 1, one = 1;
		int res = 0;
		if(n == 0)
			return zero;
		else if(n == 1)
			return one;
		else {
			for(int i=1; i<n; i++) {
				res = (zero + one) % 1000000007;
				zero = one;
				one = res;
			}
		}
		
		return res;
    }
	
	public static void main(String[] args) {
		Test_9_x_add_2y test = new Test_9_x_add_2y();
		int result = test.numWays(7);
		System.out.println(result);
	}
}
