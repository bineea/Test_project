package test_project.exercise.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ����һ�������� target ��������к�Ϊ target ���������������У����ٺ�������������
 * �����ڵ�������С�������У���ͬ���а����׸����ִ�С�������С�
 * 
 * ���磺
 * ���룺target = 15
 * �����[[1,2,3,4,5],[4,5,6],[7,8]]
 * 
 * һԪ���η���
 * ax^2 + bx + c = 0
 * �� = b^2-4ac;x=(-b �� ��(b^2-4ac))/2a
 * 
 * @author binee
 *
 */
public class Test_17_find_continue_seq {

	public int[][] findContinuousSequence_BL(int target) {

		int[][] result = new int[0][0];
		int x=0,y=0;
		List<int[]> resList = new ArrayList<int[]>();
		int middle = (int)Math.floor((double)target / (double) 2);
		for(int i=32719; i<=middle; i++) {
			double check = Math.pow((double)(2 * i - 1), 2) + (double)(4 * 1 * 2 * target);
			if(check >= 0) {
				double n = ((double)(1-2*i) + (double)Math.sqrt((double)(2*i-1)*(double)(2*i-1) + (double)(4 * 1 * 2 * target))) * 0.5;
				if(n == (int)n) {
					x += 1;
					y = (int) Math.max(y, n);
					int[] res = new int[(int)n];
					for(int z=0; z<n; z++) {
						res[z] = i + z;
					}
					resList.add(res);
				}
			}
		}
		if(resList.size() > 0) {
			result = new int[x][y];
			for(int m=0; m<resList.size(); m++) {
				result[m] = resList.get(m);
			}
		}
		return result;
    }
	
	public int[][] findContinuousSequence_FINAL(int target) {

		List<int[]> result = new ArrayList<>();
		int i = 1;

		while (target > 0) {
			//target -= i++;  ==> target -= i; i += 1;
			/**
			 * �Ƶ���ǰ������d == 1
			 * ��target=a1*n+[n*(n-1)]/2����֪target=[2*a1 + (n-1)] * n / 2����a1=[target - n*(n-1)/2] / n���ر�ע��n*(n-1)/2Ϊ1��n-1�ĺͣ�����
			 * 
			 * target -= i++; ʵ��Ϊ target - (1 + 2 + 3 + ... + n-1)
			 * 
			 * ͨ������ѭ����ȡa1
			 */
			target -= i++;
			if (target > 0 && target % i == 0) {
				int[] array = new int[i];
				for (int k = target / i, j = 0; k < target / i + i; k++, j++) {
					array[j] = k;
				}
				result.add(array);
			}
		}
		Collections.reverse(result);
		return result.toArray(new int[0][]);
	}
	
	public static void main(String[] args) {
		Test_17_find_continue_seq test = new Test_17_find_continue_seq();
		int[][] seq1 = test.findContinuousSequence_FINAL(9);
		for(int x=0; x<seq1.length; x++) {
			for(int y=0; y<seq1[x].length; y++) {
				System.out.print(seq1[x][y]);
			}
			System.out.println("\n");
		}
	}
}
