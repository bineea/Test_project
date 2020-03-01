package test_project.leetcode;

/**
 * һֻ����һ�ο�������1��̨�ף�Ҳ��������2��̨�ס������������һ�� n ����̨���ܹ��ж�����������
 * ����Ҫȡģ 1e9+7��1000000007����������ʼ���Ϊ��1000000008���뷵�� 1��
 * ����˼·��
 * ��������n��̨�׹���f(n)�������������������У����ܵ����һ��ֻ���������������1��̨�׻�������2��̨�ף�
 * 
 * ��Ϊ1��̨��ʱ��ʣ��n-1��̨�ף����������f(n-1)��������
 * ��Ϊ2��̨��ʱ��ʣ��n-2��̨�ף����������f(n-2)��������
 * 
 * f(n)Ϊ�����������֮�ͣ�����f(n) = f(n-1) + f(n-2)�����ϵ�������Ϊ쳲��������У�����
 * 
 * ��Ϊn=1ʱ��f(1)=1��n=2ʱ��f(n)=2������f(0) = f(2) - f(1) = 1������
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
