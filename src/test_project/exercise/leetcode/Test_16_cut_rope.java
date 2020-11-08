package test_project.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ����һ������Ϊ n �����ӣ�������Ӽ����������ȵ� m �Σ�m��n����������n>1����m>1����ÿ�����ӵĳ��ȼ�Ϊ k[0],k[1]...k[m] ������ k[0]*k[1]*...*k[m] ���ܵ����˻��Ƕ��٣�
 * ���磬�����ӵĳ�����8ʱ�����ǰ������ɳ��ȷֱ�Ϊ2��3��3�����Σ���ʱ�õ������˻���18��
 * 
 * ����˼·��
 * 1.int[] res = {0, 0, 1, 2, 4, 6, 9, 12, 18, 27, 36, 54, 81, 108, 162, 243, 324, 486, 729, 972, 1458, 2187, 2916, 4374, 6561, 8748, 13122, 19683, 26244, 39366, 59049, 78732, 118098, 177147, 236196, 354294, 531441, 708588, 1062882, 1594323, 2125764, 3188646, 4782969, 6377292, 9565938, 14348907, 19131876, 28697814, 43046721, 57395628, 86093442, 129140163, 172186884, 258280326, 387420489, 516560652, 774840978, 1162261467, 1549681956};
 * ���ݽ��۵��ƹ��ɣ�
 * n=4��2��2
 * n=5��2��3
 * n=6��3��3
 * n=7��2��3��2
 * n=8��2��3��3
 * n=9��3��3��3
 * n=10��2��2��3��3
 * n=11��2��3��3��3
 * n=12��3��3��3��3
 * n=13��2��2��3��3��3
 * n=14��2��3��3��3��3
 * 
 * ����2����0�Ρ�1�Ρ�2�Σ�������3���
 * 
 * 2.���Զ����¡�һ�ֺ�ֱ�۵ı����ⷨ�������оٳ����е�������ҵ��˻������Ǹ��⡣
 * �� F(n)Ϊ����Ϊ n �����ӿ��Եõ������˻�
 * ÿһ������n�϶����Բ����������������i��n-i��n = i + (n - i), ����i * (n - i)��һ�������˻���(n - i)��һ������F(n - i)��
 * ��������n���Ϊ���ٸ��������������Ա�ʾΪ  i �� (n - i)�����������������˻����� i * F(n - i) �� i * (n - i)�е����ֵ
 * ����F(n) = max(i * (n - 1), i * F(n - 1)), i=1,2,3,...n-2;
 * ���԰���� F(n) ������ֽ�����F(n-1)�����⣬�Դ����ƣ�ֱ����⵽ F(2)ʱ��F(2)=1�����ƻ�ȥ������͵õ��˽�������õ��ľ��Ƿ��ε�˼�롣
 * 
 * 3.���������ϡ�ʹ�ö�̬�滮������ֵ֪ F(2)�𲽵�����Ŀ��ֵ F(n)������һ���Ե����ϵķ���
 * F(n) = max(i * (n - 1), i * F(n - 1));��F(2)�𲽵�����F(n)
 * 
 * @author binee
 *
 */
public class Test_16_cut_rope {

	//int�������ݿ������
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
			//�������ȡ������Ļ������޷�ʹ��max�����Ƚ����ݴ�С������
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