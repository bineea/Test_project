package test_project.leetcode;

/**
 * 
 * ����һ������Ϊ n �����ӣ�������Ӽ����������ȵ� m �Σ�m��n����������n>1����m>1����ÿ�����ӵĳ��ȼ�Ϊ k[0],k[1]...k[m] ������ k[0]*k[1]*...*k[m] ���ܵ����˻��Ƕ��٣����磬�����ӵĳ�����8ʱ�����ǰ������ɳ��ȷֱ�Ϊ2��3��3�����Σ���ʱ�õ������˻���18��
 * ����Ҫȡģ 1e9+7��1000000007����������ʼ���Ϊ��1000000008���뷵�� 1��
 * 
 * ����˼·��
 * �������ȿ��Ƕ���һ�γ�n�����ӣ����ǿ����г��Ľ������ʲô��
 * 1������� ���ᣬ��Ϊ1 * (k - 1) < k, ֻҪ��1���κ�һ��������Ƭ�������һ����и������ֵ
 * 2����
 * 3����
 * 4������ ���������2��Ч���ͱ���һ�������Ҳ������
 * 5���Ͽ����� �����ԣ���Щ���ӱ������Ϊ����һ�ֲ𷨱Ȳ�����ţ������� k / 2 �� k - k / 2
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
