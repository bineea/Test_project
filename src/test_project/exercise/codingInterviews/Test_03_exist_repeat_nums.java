package test_project.exercise.codingInterviews;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 *�ҳ��������ظ�������
 *��һ������Ϊn����������������ֶ���0~n-1�ķ�Χ�ڡ�������ĳЩ�������ظ��ģ�����֪���м��������ظ��ˣ�Ҳ��֪��ÿ�������ظ��˼��Ρ����ҳ�����������һ���ظ�������
 *���磺������볤��Ϊ7������{2��3��1��0��2��5��3}����ô��Ӧ��������ظ�������2����3��
 *
 *1.����hash
 *2.��Ϊ������Ϊn����������������ֶ���0~n-1�ķ�Χ�ڡ�������numbers[n-1]����Խ�磬�����������������iӦ�ó������±�Ϊi��λ��
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
	 * ���Ƚ����鰴�մ�С������������Ȼ���ա��������������iӦ�ó������±�Ϊi��λ�á��Ĺ���ɸѡ�ظ�����
	 * @return
	 */
	public Integer sortNumAndSelect(int[] numbers) {
		
		return null;
	}
	
	/**
	 * ͨ��HashSetɸѡ�ظ�����
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
