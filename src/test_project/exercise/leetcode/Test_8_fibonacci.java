package test_project.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * дһ������������ n ����쳲�������Fibonacci�����еĵ� n �쳲��������еĶ������£�
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), ���� N > 1.
 * 쳲����������� 0 �� 1 ��ʼ��֮���쳲�������������֮ǰ��������Ӷ��ó���
 * ����Ҫȡģ 1e9+7��1000000007����������ʼ���Ϊ��1000000008���뷵�� 1��
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
