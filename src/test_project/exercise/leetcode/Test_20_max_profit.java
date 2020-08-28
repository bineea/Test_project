package test_project.exercise.leetcode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票
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
