package test_project.leetcode;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 
 * 例如： 
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 
 * 解题思路：
 * 
 * 
 * @author binee
 *
 */
public class Test_19_coin_change {

	public int coinChange(int[] coins, int amount) {
		Arrays.sort(coins);
		if(amount <=0 )
			return 0;
		int result = Integer.MAX_VALUE;
		result = handleCoinChange(coins, amount, coins.length-1, 0, result);
		if(result > 0 && result < Integer.MAX_VALUE)
			return result;
		return -1;
    }
	
	public int handleCoinChange(int[] coins, int amount, int maxCoinsNo, int count, int result) {
		for(int i=maxCoinsNo; i>=0; i--) {
			if(coins[i] > amount) {
				continue;
			} else if(coins[i] == amount) {
				result = Math.min(count + 1, result);
				return result;
			} else {
				int nextAmount = amount % coins[i];
				int multiple = (int) Math.floor(amount / coins[i]);
				if(nextAmount == 0) {
					result = Math.min(count + multiple, result);
					return result;
				} else {
					if(i-1 < 0) {
						break; 
					}
					//1.将result作为方法参数，可以实现在result计算前进行比较，提前终止不必要的递归
					//2.如果方法进行到此处，则此次放回result最小为count + x + 1，所以提前比较count + x + 1与result，终止不必要的递归
					for(int x=multiple; x>=1 && count + x + 1 <= result; x--) {
						result = handleCoinChange(coins, amount-x*coins[i], i-1, count + x , result);
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Test_19_coin_change test = new Test_19_coin_change();
		System.out.println(test.coinChange(new int[] {1,3,5}, 8));
		System.out.println(test.coinChange(new int[] {1,2,5}, 11));
		System.out.println(test.coinChange(new int[] {186,419,83,408}, 6249));
		System.out.println(test.coinChange(new int[] {288,160,10,249,40,77,314,429}, 9208));
	}
}
