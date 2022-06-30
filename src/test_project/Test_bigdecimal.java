package test_project;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Test_bigdecimal {
	
	public static void main(String[] args) {
		Test_bigdecimal test = new Test_bigdecimal();
		BigDecimal bigdecimal = new BigDecimal("10001.000");
		System.out.println(test.formatSeparate1(bigdecimal));
		System.out.println(test.formatSeparate2(bigdecimal));
		System.out.println(test.formatSeparate3(bigdecimal));
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
	

}
