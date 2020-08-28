package test_project.exercise.leetcode;

/**
 * ����һ�����飬���ĵ� i ��Ԫ����һ֧������Ʊ�� i ��ļ۸�
 * ��������ֻ�������һ�ʽ��ף������������һ֧��Ʊ�������һ���㷨�����������ܻ�ȡ���������
 * ע���㲻���������Ʊǰ������Ʊ
 * 
 * 
 * @author bineea
 *
 */
public class Test_20_max_profit {

	public int maxProfit_BL(int[] prices) {
		int res = 0;
		for(int x=0; x<prices.length-1; x++) {
			for(int y=x+1; y<prices.length; y++) {
				if(prices[y]-prices[x]>0) {
					res = Math.max(res, prices[y]-prices[x]);
				}
			}
		}
		return res;
    }
	
	public int maxProfit_FINAL(int[] prices) {
		int res = 0;
		
		int min_price = Integer.MAX_VALUE;
		
		for(int i=0; i<prices.length; i++) {
			if(prices[i] < min_price)
				min_price = prices[i];
			res = Math.max(res, prices[i] - min_price);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		Test_20_max_profit test = new Test_20_max_profit();
		System.out.println(test.maxProfit_FINAL(new int[] {7,1,5,3,6,4}));
		System.out.println(test.maxProfit_FINAL(new int[] {7,6,4,3,1}));
	}
}
