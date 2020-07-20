package test_project.basic.sort;

/**
 * ��������
 * ������Ԫ�أ�������������õ�������Ԫ�شӺ�ǰ���бȽϣ�
 * �����ǰԪ��ֵ�ȴ���Ԫ��ֵ������λ���������ڵĺ�һ��λ�ã�
 * ����ֱ�ӽ�����Ԫ�ز��뵱ǰԪ�����ڵĺ�һλ�ã���Ϊ˵���Ѿ��ҵ�����������λ��
 * 
 *
 * @author bineea
 *
 */

public class Test_insert_sort {
	
	public static void main(String[] args) {
		Test_insert_sort test = new Test_insert_sort();
		test.insertSort(new int[] {1,3,2,8,5,4});
	}

	public void insertSort(int[] num) {
		if(num == null || num.length < 2) {
			System.out.println("�����������޷���������Ϊ�ջ������鳤��С��2");
			return;
		}
		//�ӵ�һ��Ԫ�ؿ�ʼ�������϶���һ��Ԫ��Ϊ�Ѿ�������
		for(int i=1; i<num.length; i++) {
			int insertValue = num[i];
			//��ǰ����һ�����������ֵ��insertValue���ʱ��,һֱѭ����ǰ����,��insertValue���뵽�⴮ֵǰ��
			for(int x=i-1; x>=0; x--) {
				if(num[x] > insertValue) {
					num[x+1] = num[x];
					num[x] = insertValue;
				} else {
					break;
				}
			}
			
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
}
