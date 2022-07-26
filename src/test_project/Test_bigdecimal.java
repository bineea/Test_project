package test_project;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Test_bigdecimal {
	
	public static void main(String[] args) {
		Test_bigdecimal test = new Test_bigdecimal();
		BigDecimal bigdecimal = new BigDecimal("10001.000");
//		System.out.println(test.formatSeparate1(bigdecimal));
//		System.out.println(test.formatSeparate2(bigdecimal));
//		System.out.println(test.formatSeparate3(bigdecimal));
//		test.testAdd();
//		test.testDivide();
	}
	
	/**
	 * 添加千位分隔符，但是丢失小数位
	 * @param bigdecimal
	 * @return
	 */
	private String formatSeparate1(BigDecimal bigdecimal) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		return formatter.format(bigdecimal);
	}
	
	/**
	 * 添加千位分隔符，但是需要手动指定小数位
	 * @param bigdecimal
	 * @return
	 */
	private String formatSeparate2(BigDecimal bigdecimal) {
		return String.format("%,.4f", bigdecimal);
	}
	
	/**
	 * 添加千位分隔符，但是丢失小数位
	 * @param bigdecimal
	 * @return
	 */
	private String formatSeparate3(BigDecimal bigdecimal) {
		NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
		return String.format(formatter.format(bigdecimal));
	}
	
	
	private void testAdd() {
		BigDecimal bd1 = BigDecimal.ZERO;
		BigDecimal bd2 = new BigDecimal("9000001000000000000");
		System.out.println(bd1);
		System.out.println(bd1.add(bd2));
		System.out.println(bd1);
		
		BigDecimal bd3 = bd1.add(bd2);
		BigDecimal bd4 = bd1.add(bd2);
		System.out.println(bd3.equals(bd4));
	}
	
	private void testDivide() {
		BigDecimal bd1 = new BigDecimal("10");
		BigDecimal bd2 = new BigDecimal("3");
		
		//除法结果为无限小数，则需要指定保留小数位
		System.out.println(bd1.divide(bd2));
		//System.out.println(bd1.divide(bd2, 8));
	}

}
