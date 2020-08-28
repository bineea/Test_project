package test_project.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * @author binee
 *
 */
public class Test_8_fibonacci {
	
	Map<Integer, Integer> fibBLMap = new HashMap<Integer, Integer>();

	public int fib_BL(int n) {
		int result = 0;
		if(n == 0) result = 0;
		if(n == 1) result = 1;
		if(n > 1) {
			if(fibBLMap.get(n) != null)
				result = fibBLMap.get(n) % 1000000007;
			else
				result = (fib_BL(n-1) + fib_BL(n-2)) % 1000000007;
		}
		fibBLMap.put(n, result);
		return result;
    }
	
	public int fib_FINAL(int n) {
		int result = 0;
		if(n == 0) result = 0;
		if(n == 1) result = 1;
		int fibOne=0,fibTwo=1;
		for(int x=2; x<=n; x++ ) {
			result = (fibOne + fibTwo) % 1000000007;
			fibOne = fibTwo;
			fibTwo = result;
		}
		return result;
    }
	
	public static void main(String[] args) {
		Test_8_fibonacci test = new Test_8_fibonacci();
		int result = test.fib_FINAL(45);
		System.out.println(result);
	}
}
