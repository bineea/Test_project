package test_project.basic.sort;

/**
 * ��������
 * ͨ��һ�����򽫴��ż�¼�ָ��ɶ����������֣�����һ���ּ�¼�Ĺؼ��־�����һ���ֵĹؼ���С����ɷֱ���������ּ�¼�������������Դﵽ������������
 * 
 * ������������һ��Ԫ�أ�ͨ��ѡ�����еĵ�һ��Ԫ�أ�����Ϊ ����׼����pivot����
 * �����������У�����Ԫ�رȻ�׼ֵС�İڷ��ڻ�׼ǰ�棬����Ԫ�رȻ�׼ֵ��İ��ڻ�׼�ĺ��棨��ͬ�������Ե���һ�ߣ�������������˳�֮�󣬸û�׼�ʹ������е��м�λ�á������Ϊ������partition��������
 * �ݹ�ģ�recursive����С�ڻ�׼ֵԪ�ص������кʹ��ڻ�׼ֵԪ�ص�����������
 * 
 * @author bineea
 *
 */
public class Test_quick_sort {
	
	public static void main(String[] args) {
		Test_quick_sort test = new Test_quick_sort();
		test.quickSort(new int[] {1,3,2,8,5,4}, 0, 6);
	}
	
	public void quickSort(int[] num, int start, int length) {
		if(num == null || num.length < 2 || length < 2) {
			System.out.println("�����������޷���������Ϊ�ջ������鳤��С��2");
			return;
		}
		int pivot = num[start];
		int pivotIndex = start;
		for(int x=1; x<length; x++) {
			if(pivot > num[start+x]) {
				num[pivotIndex] = num[start+x];
				num[start+x] = pivot;
				pivotIndex = start+x;
			} 
		}
		
		quickSort(num, start, pivotIndex-start+1);
		quickSort(num, pivotIndex+1, length-pivotIndex+start-1);
		
		if(num.length == length) {
			for(int i=0; i<num.length; i++) {
				System.out.print(num[i] + ";");
			}
		}
	}
}
