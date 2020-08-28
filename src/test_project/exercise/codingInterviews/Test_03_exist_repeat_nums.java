package test_project.exercise.codingInterviews;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 *找出数组中重复的数字
 *在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字
 *例如：如果输入长度为7的数组{2，3，1，0，2，5，3}，那么对应的输出是重复的数字2或者3。
 *
 *1.利用hash
 *2.因为“长度为n的数组里的所有数字都在0~n-1的范围内”，所以numbers[n-1]不会越界，而且数组排序后数字i应该出现在下标为i的位置
 *
 * @author binee
 *
 */
public class Test_03_exist_repeat_nums {

	public static void main(String[] args) {
		Test_03_exist_repeat_nums test = new Test_03_exist_repeat_nums();
		System.out.println(test.selectByHash(new int[] {2,3,1,0,2,5,3}));
	}
	
	
	/**
	 * 首先将数组按照从小到大正序排序；然后按照“数组排序后数字i应该出现在下标为i的位置”的规则筛选重复数字
	 * @return
	 */
	public Integer sortNumAndSelect(int[] numbers) {
		
		return null;
	}
	
	/**
	 * 通过HashSet筛选重复数字
	 * @return
	 */
	public Integer selectByHash(int[] numbers) {
		if(numbers == null || numbers.length <= 0)
			return null;
		Set<Integer> opSet = new HashSet<>();
		for(int i=0; i<numbers.length; i++) {
			if(opSet.contains(numbers[i])) {
				return numbers[i];
			} else {
				opSet.add(numbers[i]);
			}
		}
		return null;
	}
}
