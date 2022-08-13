package test_project;

public class Test_hex {
	
	
	public static void main(String[] args) {
		
		System.out.println(Test_hex.intToHex(12));
		
		System.out.println(Test_hex.byteArrToHex("12".getBytes()));
	}
	
	/**
	  * 十进制转十六进制字符串
	  * @param param 十进制参数
	  * @return 十六进制字符串
	  */
	private static String intToHex(int param){
	     String result = Integer.toHexString(param);
	     return result.length()%2==1?"0"+result:result;
	}

	private static final char HexCharArr[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
	/**
	     * 字节数组转化为十六进制字符串
	     * @param content 字节数组
	     * @return 十六进制字符串
	     */
	    public static String byteArrToHex(byte[] content) {
	        char[] strArr = new char[content.length * 2];
	        int i = 0;
	        for (byte bt : content) {
	            strArr[i++] = HexCharArr[bt>>>4 & 0xf];
	            strArr[i++] = HexCharArr[bt & 0xf];
	        }
	        return new String(strArr);
	    }
}
