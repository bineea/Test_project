package test_project;

/**
 * java 运算符
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
	
	//无符号右移
	public int wufuhao_youyi(int x, int rightCount) {
		int res = x >>> rightCount;
		System.out.println(x+"无符号右移 "+ rightCount+"位：res:"+res+";32位的二进制码:"+toBinaryString(res));
		return res;
	}
	
	//符号右移
	public int fuhao_youyi(int x, int rightCount) {
		int res = x >> rightCount;
		System.out.println(x+"符号右移 "+ rightCount+"位：res:"+res+";32位的二进制码:"+toBinaryString(res));
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
