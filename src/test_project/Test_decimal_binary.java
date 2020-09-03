package test_project;

/**
 * 十进制与二进制转换
 * 
 * 1.十进制转二进制
 * 用2整除十进制整数，可以得到一个商和余数；再用2去除商，又会得到一个商和余数，如此进行，直到商为小于1时为止，
 * 然后把先得到的余数作为二进制数的低位有效位，后得到的余数作为二进制数的高位有效位，依次排列起来。
 * 
 * 2.二进制转十进制
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
