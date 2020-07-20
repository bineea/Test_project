package test_project.basic.sort;

/**
 * ð������
 * �Ƚ����ڵ�Ԫ�ء������һ���ȵڶ����󣬾ͽ�������������
 * ��ÿһ������Ԫ����ͬ���Ĺ������ӿ�ʼ��һ�Ե���β�����һ�ԡ�����һ�㣬����Ԫ��Ӧ�û����������� 
 * ������е�Ԫ���ظ����ϵĲ��裬�������һ����
 * ����ÿ�ζ�Խ��Խ�ٵ�Ԫ���ظ�����Ĳ��裬ֱ��û���κ�һ��������Ҫ�Ƚϡ�
 * @author bineea
 *
 */
public class Test_bubbling_sort {
	
	public static void main(String[] args) {
		Test_bubbling_sort test = new Test_bubbling_sort();
		test.bubblingSort(new int[] {1,3,2,8,5,4});
	}

	public void bubblingSort(int[] num) {
		//��һ��forѭ������ð�ݴ�����ÿ��ð�ݽ����ֵ�ǰ������ֵ������Ϊn��������Ҫn-1��������ֵ������ѭ��Ϊ0~n-2��n-1��
		for(int x=0; x<num.length-1; x++) {
			//�ڶ���forѭ���ȶ����ڵ�������ֵ���ڶ���ѭ������ѭ����arr.length - 1����Ϊ�����xѭ������һ��,˵���������Ͷ���һ���ź���Ĵ�����
			for(int i=0; i<num.length-1-x; i++) {
				int initValue = num[i];
				if(initValue > num[i+1]) {
					num[i] = num[i+1];
					num[i+1] = initValue;
				}
			}
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
}
