package test_project;

/**
 * java �����
 * @author binee
 *
 */
public class Test_operator {

	public static void main(String[] args) {
		Test_operator test = new Test_operator();
		System.out.println(test.toBinaryString(20));
		test.wufuhao_youyi(20, 2);
		test.fuhao_youyi(20, 2);
		System.out.println(test.toBinaryString(-20));
		test.wufuhao_youyi(-20, 2);
		test.fuhao_youyi(-20, 2);
	}
	
	//�޷�������
	public int wufuhao_youyi(int x, int rightCount) {
		int res = x >>> rightCount;
		System.out.println(x+"�޷������� "+ rightCount+"λ��res:"+res+";32λ�Ķ�������:"+toBinaryString(res));
		return res;
	}
	
	//��������
	public int fuhao_youyi(int x, int rightCount) {
		int res = x >> rightCount;
		System.out.println(x+"�������� "+ rightCount+"λ��res:"+res+";32λ�Ķ�������:"+toBinaryString(res));
		return res;
	}
	
	public String toBinaryString(int res) {
		String str = Integer.toBinaryString(res);
		if(str.length() < 32) {
			str = String.format("%0"+(32-str.length())+"d", 0) + str; 
		}
		return str;
	}
}
