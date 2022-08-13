package test_project;

public class Test_hex {
	
	
	public static void main(String[] args) {
		
		System.out.println(Test_hex.intToHex(12));
		
		System.out.println(Test_hex.byteArrToHex("12".getBytes()));
	}
	
	/**
	  * ʮ����תʮ�������ַ���
	  * @param param ʮ���Ʋ���
	  * @return ʮ�������ַ���
	  */
	private static String intToHex(int param){
	     String result = Integer.toHexString(param);
	     return result.length()%2==1?"0"+result:result;
	}

	private static final char HexCharArr[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
	/**
	     * �ֽ�����ת��Ϊʮ�������ַ���
	     * @param content �ֽ�����
	     * @return ʮ�������ַ���
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
