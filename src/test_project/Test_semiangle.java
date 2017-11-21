package test_project;

public class Test_semiangle {
	
	public static String toDBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
        	if(c[i] >= '\u4e00' && c[i] <= '\u9fa5'){
        		continue;
        	}
        	else if (c[i] == '\u3000') {
        		c[i] = ' ';
        	} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
        		c[i] = (char) (c[i] - 65248);
        	}
        }
	    String returnString = new String(c);
        return returnString;
	}
	public static void main(String[] args)
	{
		String num="３７０７８３１９８５０９１８１３７７ｘａｂｃ是福建省的肌大家疯狂的世界疯狂的萨菲较大的身份（）（）（）";
		String xxx="人生如梦，一樽还酹江月！！！（苏轼）";
		System.out.println(num);
		System.out.println(Test_semiangle.toDBC(num));
		System.out.println(Test_semiangle.toDBC(xxx));
		num="370783198509181377";
		System.out.println(Test_semiangle.toDBC(num));
	}
}
