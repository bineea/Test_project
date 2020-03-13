package test_project.leetcode;

/**
 * 
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 解题思路：
 * 我们首先考虑对于一段长n的绳子，我们可以切出的结果包含什么？
 * 1会包含吗？ 不会，因为1 * (k - 1) < k, 只要把1和任何一个其他的片段组合在一起就有个更大的值
 * 2可以
 * 3可以
 * 4可以吗？ 它拆成两个2的效果和本身一样，因此也不考虑
 * 5以上可以吗？ 不可以，这些绳子必须拆，因为总有一种拆法比不拆更优，比如拆成 k / 2 和 k - k / 2
 * 
 * @author binee
 *
 */
public class Test_21_cut_rope {

	public int cuttingRope(int n) {
		if(n == 2) {
            return 1;
        }
        if(n == 3){
            return 2;
        }
        int mod = (int)1e9 + 7;
        long res = 1L;
        while(n > 4) {
            res *= 3;
            res %= mod;
            n -= 3;
        }
        return (int)(res * n % mod);
	}
	
	public static void main(String[] args) {
		Test_21_cut_rope test = new Test_21_cut_rope();
		System.out.println(test.cuttingRope(2));
		System.out.println(test.cuttingRope(10));
		System.out.println(test.cuttingRope(120));
	}
}
