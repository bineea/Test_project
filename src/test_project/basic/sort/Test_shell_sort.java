package test_project.basic.sort;

/**
 * ϣ������
 * ϣ�������ǰѼ�¼���±��һ���������飬��ÿ��ʹ��ֱ�Ӳ��������㷨����
 * ���������𽥼��٣�ÿ������Ĺؼ���Խ��Խ�࣬����������1ʱ�������ļ�ǡ���ֳ�һ�飬�㷨����ֹ��
 * 
 * ѡ��һ����������t1��t2������tk������ti>tj��tk=1��
 * ���������и���k�������н���k ������
 * ÿ�����򣬸��ݶ�Ӧ������ti�����������зָ�����ɳ���Ϊm �������У��ֱ�Ը��ӱ����ֱ�Ӳ������򡣽���������Ϊ1 ʱ������������Ϊһ�������������ȼ�Ϊ�������еĳ��ȡ�
 * 
 * �ڴ�����ѡ������gap=length/2����С����������gap = gap/2�ķ�ʽ����������ѡ�����ǿ�����һ����������ʾ��{n/2,(n/2)/2...1}����Ϊ�������С�
 * ϣ��������������е�ѡ����֤���Ǹ���ѧ���⣬����ѡ���������������ǱȽϳ��õģ�Ҳ��ϣ���������������Ϊϣ������������ʵ����������в������ŵġ�
 * 
 * eg������{8,9,1,7,2,3,5,4,6,0}
 * ��ʼ����gap=length/2=5����ζ���������鱻��Ϊ5�飬{8,3}��{9,5}��{1,4}��{7,6}��{2,0}������5��ֱ���в�������
 *Ȼ����С����gap=5/2=2�����鱻��Ϊ2�飬{3,1,0,9,7}��{5,6,8,4,2}���ٶ���2��ֱ���в�������
 *�������С����gap=2/2=1����ʱ����Ϊ1�飬{0,2,1,4,3,5,7,6,9,8}���ٽ��в������򼴿ɽ��� 
 * 
 * @author bineea
 *
 */
public class Test_shell_sort {

	public static void main(String[] args) {
		Test_shell_sort test = new Test_shell_sort();
		test.shellSort(new int[] {8,9,1,7,2,3,5,4,6,0});
	}
	
	public void shellSort(int[] num) {
		if(num == null || num.length < 2) {
			System.out.println("�����������޷���������Ϊ�ջ������鳤��С��2");
			return;
		}
		
		int gap = num.length / 2;
		while(gap >= 1) {
			for(int i=0; i<num.length; i+=gap) {
				this.insertSort(num, i, gap);
			}
			gap = gap / 2;
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
	
	public void insertSort(int[] num, int start, int gap) {
		
		for(int x=start+gap; x<num.length; x+=gap) {
			int insertIndex = x;
			for(int y=x-gap; y>=0; y-=gap) {
				if(num[y] > num[insertIndex]) {
					int temp = num[insertIndex];
					num[insertIndex] = num[y];
					num[y] = temp;
					insertIndex = y;
				}
			}
			
		}
	}
}
