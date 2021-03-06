package test_project.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 
 * 解题思路：
 * 1.int[] res = {0, 0, 1, 2, 4, 6, 9, 12, 18, 27, 36, 54, 81, 108, 162, 243, 324, 486, 729, 972, 1458, 2187, 2916, 4374, 6561, 8748, 13122, 19683, 26244, 39366, 59049, 78732, 118098, 177147, 236196, 354294, 531441, 708588, 1062882, 1594323, 2125764, 3188646, 4782969, 6377292, 9565938, 14348907, 19131876, 28697814, 43046721, 57395628, 86093442, 129140163, 172186884, 258280326, 387420489, 516560652, 774840978, 1162261467, 1549681956};
 * 根据结论倒推规律：
 * n=4：2，2
 * n=5：2，3
 * n=6；3，3
 * n=7；2，3，2
 * n=8；2，3，3
 * n=9；3，3，3
 * n=10；2，2，3，3
 * n=11；2，3，3，3
 * n=12；3，3，3，3
 * n=13；2，2，3，3，3
 * n=14；2，3，3，3，3
 * 
 * 发现2出现0次、1次、2次，其他由3填充
 * 
 * 2.【自顶向下】一种很直观的暴力解法，就是列举出所有的情况，找到乘积最大的那个解。
 * 设 F(n)为长度为 n 的绳子可以得到的最大乘积
 * 每一个数字n肯定可以拆分两个正整数，即i和n-i，n = i + (n - i), 但是i * (n - i)不一定是最大乘积，(n - i)不一定大于F(n - i)，
 * 而且无论n拆分为多少个正整数，都可以表示为  i 和 (n - i)，而这两个数的最大乘积就是 i * F(n - i) 和 i * (n - i)中的最大值
 * 所以F(n) = max(i * (n - 1), i * F(n - 1)), i=1,2,3,...n-2;
 * 所以把求解 F(n) 的问题分解成求解F(n-1)的问题，以此类推，直到求解到 F(2)时，F(2)=1，递推回去，问题就得到了解决。这用到的就是分治的思想。
 * 
 * 3.【自下向上】使用动态规划，从已知值 F(2)逐步迭代到目标值 F(n)，它是一种自底向上的方法
 * F(n) = max(i * (n - 1), i * F(n - 1));从F(2)逐步迭代至F(n)
 * 
 * @author binee
 *
 */
public class Test_16_cut_rope {

	//int类型数据可能溢出
	public int cuttingRope_law(int n) {
		int count = n / 3;
		int x = n % 3;
		int res = 0;
		if(n >= 4) {
			if(x == 1) {
				res = (int) (2 * 2 * Math.pow(3, count - 1));
			} else if(x == 2) {
				res = (int) (2 * 1 * Math.pow(3, count));
			} else {
				res = (int) (Math.pow(3, count));
			}
		} else {
			if(n == 0 || n == 1)
				res = 0;
			else if(n == 2)
				res = 1;
			else if(n == 3)
				res = 2;
		}
		return res;
    }
	
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public int cuttingRope_BL(int n) {
		if(n == 0 || n == 1)
			return 0;
		if(n == 2)
			return 1;
		if(n == 3)
			return 2;
		if(map.get(n) != null) {
			return map.get(n);
		}
		int res = 0;
		for(int i=1; i<=n-2; i++) {
			//如果进行取余运算的话，就无法使用max函数比较数据大小！！！
			res = Math.max(res, Math.max(i * cuttingRope_BL(n-i), i * (n-i))) ;
		}
		map.put(n, res);
		return res;
		
	}
	
	public int cuttingRepo_FINAL(int n) {
		int res = 0;
		
		int[] dp = new int[n+1];
		dp[2] = 1;
		for(int x=3; x<=n; x++) {
			for(int y=1; y<x; y++) {
				dp[x] = Math.max(dp[x], Math.max(y * (x - y), y * dp[x - y]));
			}
		}
		res = dp[n];
		return res;
	}
	
	public static void main(String[] args) {
		Test_16_cut_rope test = new Test_16_cut_rope();
		System.out.println(test.cuttingRepo_FINAL(10));
	}
}
