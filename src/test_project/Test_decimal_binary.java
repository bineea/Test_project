package test_project;

/**
 * ʮ�����������ת��
 * 
 * 1.ʮ����ת������
 * ��2����ʮ�������������Եõ�һ���̺�����������2ȥ���̣��ֻ�õ�һ���̺���������˽��У�ֱ����ΪС��1ʱΪֹ��
 * Ȼ����ȵõ���������Ϊ���������ĵ�λ��Чλ����õ���������Ϊ���������ĸ�λ��Чλ����������������
 * 
 * 2.������תʮ����
 * @author bineea
 *
 */
public class Test_decimal_binary {

	public static void main(String[] args) {
		Test_decimal_binary test = new Test_decimal_binary();
		System.out.println("x ---> "+test.decimal2binary(-16));
		System.out.println("1 / 2 = "+String.valueOf(1 / 2));
		System.out.println("-2 % 4 = "+String.valueOf(-2 & 3));
		System.out.println("-2 % 4 = "+String.valueOf(-2 % 4));
	}
	
	public String decimal2binary(int xValue) {
		String binaryStr = "";
		int x = Math.abs(xValue);
		int res = x & 1;
		binaryStr = String.valueOf(res) + binaryStr;
		while (Math.abs(x=(x-res) / 2) >= 1) {
			res = x & 1;
			binaryStr = String.valueOf(res) + binaryStr;
		}
		if(Math.abs(x) < 1) {
			binaryStr = String.valueOf(x) + binaryStr;
		}
		return String.valueOf(xValue<0?1:0) + String.format("%0"+(31 - binaryStr.length())+"d", 0) + binaryStr;
	}
	
}
