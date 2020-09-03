package test_project.exercise.other;

/**
 * ����Բ���� 
 * 
 * �߳�Ϊ2r����������Ƕ��һ����rΪ�뾶��Բ��
 * InsideCircle��� = ��rΪ�뾶��Բ���  = ��*r*r
 * ��������� = 2r*2r
 * ���ԣ� InsideCircle��� / ��������� = (��*r*r) / (2r*2r) = ��/4
 * ���ݹ��ɶ�������Բ���ϵĵ���ֱ����������߶��ṹ��һ��ֱ�������Σ����c*c <= 2r*2r���ͱ�ʾ������λ��Բ�ڣ�
 * �����������ɵ�������뵽�������У���ô����InsideCircle�ĵ�ĸ���ռ�����ı���Ҳ�����Ǧ�/4���ɴ˽���ͳ�Ƴ��е�ֵ��
 * 
 * @author bineea
 *
 */
public class Test_01_calculate_Pi {

	public static void main(String[] args) {
		Test_01_calculate_Pi test = new Test_01_calculate_Pi();
		System.out.println("Math.PI:"+Math.PI);
		test.calculatePi_RANDOM();
	}
	
	//�뾶Ϊ1d
	public void calculatePi_RANDOM() {
		
		double all=0d,yx=0d,d=0d;
		for(int i=0; i<1000000; i++) {
			double x = Math.random() * 2;
			double y = Math.random() * 2;
			if(y >= 1d) {
				d = (y-1) * (y-1) + x * x + (y-1) * (y-1) + (2-x) * (2-x);
			} else {
				d = (1-y) * (1-y) + x * x + (1-y) * (1-y) + (2-x) * (2-x);
			}
			
			if(d <= 4d) {
				yx+=1d;
			}
			all+=1d;
		}
		System.out.println(yx);
		System.out.println(all);
		System.out.println(yx / all * 4d);
	}
}
