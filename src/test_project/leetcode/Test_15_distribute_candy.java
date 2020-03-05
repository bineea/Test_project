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
 * 2.可以通过等差直接计算每个人的最终结果，实现只遍历一次！！！
 * 
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
	
	public int[] distributeCandies_FINAL(int candies, int num_people) {
		
		return null;
	}
	
	public static void main(String[] args) {
		Test_15_distribute_candy test = new Test_15_distribute_candy();
		System.out.println((int)Math.floor(Math.sqrt(8.0)));
		int[] ans1 = test.distributeCandies_BL(7, 4);
		for(int i=0; i<ans1.length; i++) {
			System.out.println(ans1[i]);
		}
	}
}
