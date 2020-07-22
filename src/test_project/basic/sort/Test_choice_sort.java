package test_project.basic.sort;

/**
 * ѡ������
 * ������δ�����������ҵ���С����Ԫ�أ���ŵ��������е���ʼλ�ã�
 * Ȼ���ٴ�ʣ��δ����Ԫ���м���Ѱ����С����Ԫ�أ��ŵ����������е�ĩβ��
 * �Դ����ƣ�ֱ������Ԫ�ؾ�������ϡ� 
 * 
 * ��ʼ״̬��������ΪR[1..n]��������Ϊ�գ�
 * ��i������(i=1,2,3��n-1)��ʼʱ����ǰ���������������ֱ�ΪR[1..i-1]��R(i..n������������ӵ�ǰ��������-ѡ���ؼ�����С�ļ�¼ R[k]���������������ĵ�1����¼R������ʹR[1..i]��R[i+1..n)�ֱ��Ϊ��¼��������1�������������ͼ�¼��������1��������������
 * ��n-1�˽��������������ˡ�
 * 
 * @author bineea
 *
 */
public class Test_choice_sort {

	public static void main(String[] args) {
		Test_choice_sort test = new Test_choice_sort();
		test.choiceSort(new int[] {1,3,2,8,5,4});
	}
	
	public void choiceSort(int[] num) {
		if(num == null || num.length < 2) {
			System.out.println("�����������޷���������Ϊ�ջ������鳤��С��2");
			return;
		}
		
		for(int i=0; i<num.length-1; i++) {
			int temp = num[i];
			int currentMinIndex = i;
			for(int x=i; x<num.length; x++) {
				if(num[x] < num[currentMinIndex]) {
					currentMinIndex = x;
				}
			}
			num[i] = num[currentMinIndex];
			num[currentMinIndex] = temp;
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
}
