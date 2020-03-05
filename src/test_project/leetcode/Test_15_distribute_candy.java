package test_project.leetcode;

/**
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 * 
 * 解题思路：
 * 等差数列公式：
 * Sn=a1*n+[n*(n-1)*d]/2或Sn=[n*(a1+an)]/2
 * an=a1+(n-1)*d
 * 
 * 1.不断遍历所有人，直到糖被分完；
 * 2.可以通过等差直接计算每个人的最终结果，实现只遍历一次！仔细计算，你会发现横、竖都是等差数列
 * 
 * 例如：输入60,  4
 * 第1次给的糖果数量：[1,2,3,4]
 * 第2次给的糖果数量：[5,6,7,8]
 * 第3次给的糖果数量：[9,10]
 * 
 * 每次给的糖果数量横、竖都是等差数列，完全是等差数列的求和！！！
 * @author bineea
 *
 */
public class Test_15_distribute_candy {

	public int[] distributeCandies_BL(int candies, int num_people) {
		int[] all_people = new int[num_people];
		
		int n = 1;
		while(candies > 0) {
			for(int i=0; i<num_people; i++) {
				int current_candies = 1 + (n - 1) * 1;
				if(candies < current_candies) {
					current_candies = candies;
				}
				all_people[i] += current_candies;
				candies -= all_people[i];
				n += 1;
			}
		}
		
		return all_people;
    }
	
	public int[] distributeCandies_TRY(int candies, int num_people) {
		int max_people = (int)Math.floor((Math.sqrt((double)1 + (double)4 * (double)1 * (double)candies * (double)2) - 1 ) * 0.5);
		int surplus_candy = candies - max_people * (max_people + 1)  / 2;
		int multiple = (int)Math.floor((double)max_people / (double)num_people);
		int[] all_people = new int[num_people];
		for(int i=0; i<num_people && i<max_people; i++) {
			if(multiple == 0 || multiple == 1) {
				all_people[i] = (int)(1 + i);
			} else {
				all_people[i] = (int)((1 + (i + 1 - 1)) * multiple + multiple * (multiple - 1) * num_people * 0.5);
			}
			
			if(num_people <= max_people && i < (max_people - num_people * multiple)) {
				all_people[i] += 1 + (i + 1 + multiple * num_people - 1) * 1;
			}
		}
		if(num_people > max_people) {
			all_people[max_people] += surplus_candy;
		} else {
			all_people[max_people - num_people * multiple] += surplus_candy;
		}
		return all_people;
	}
	
	public static void main(String[] args) {
		Test_15_distribute_candy test = new Test_15_distribute_candy();
		//System.out.println((int)Math.floor(Math.sqrt(8.0)));
		int[] ans1 = test.distributeCandies_TRY(7,4);
		for(int i=0; i<ans1.length; i++) {
			System.out.println(ans1[i]);
		}
	}
}
