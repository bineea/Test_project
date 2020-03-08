package test_project.leetcode;

import java.util.Arrays;

/**
 * ������ͬ����Ӳ�� coins ��һ���ܽ�� amount����дһ��������������Դճ��ܽ����������ٵ�Ӳ�Ҹ��������û���κ�һ��Ӳ�����������ܽ����� -1��
 * �������Ϊÿ��Ӳ�ҵ����������޵ġ�
 * 
 * ���磺 
 * ����: coins = [1, 2, 5], amount = 11
 * ���: 3 
 * ����: 11 = 5 + 5 + 1
 * 
 * ����˼·��
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
					//1.��result��Ϊ��������������ʵ����result����ǰ���бȽϣ���ǰ��ֹ����Ҫ�ĵݹ�
					//2.����������е��˴�����˴ηŻ�result��СΪcount + x + 1��������ǰ�Ƚ�count + x + 1��result����ֹ����Ҫ�ĵݹ�
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
