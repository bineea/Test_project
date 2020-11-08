package test_project.exercise.leetcode;

/**
 * 
 * �������б� [x1, y1, x2, y2] ����ʽ��ʾ������ (x1, y1) Ϊ���½ǵ����꣬(x2, y2) �����Ͻǵ����ꡣ
 * ����ཻ�����Ϊ��������������ص�����Ҫ��ȷ���ǣ�ֻ�ڽǻ�߽Ӵ����������β������ص���
 * �����������Σ��ж������Ƿ��ص������ؽ����
 * 
 * @author bineea
 *
 */

public class Test_29_rectangle_overlap {

	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		boolean res = false;
		
		if((rec2[2] > rec1[0] && rec2[3] > rec1[1] 
				&& rec2[0] < rec1[2] && rec2[1] < rec1[3]) 
            ||
            (rec1[2] > rec2[0] && rec1[3] > rec2[1] 
				&& rec1[0] < rec2[2] && rec1[1] < rec2[3])) {
			res = true;
		}
		
		return res;
    }

	public static void main(String[] args) {
		Test_29_rectangle_overlap test = new Test_29_rectangle_overlap();
		int[] array0 = new int[] {-4,-9,-2,3};
		int[] array1 = new int[] {1,-5,9,-1};
		System.out.println(test.isRectangleOverlap(array0, array1));
	}
}