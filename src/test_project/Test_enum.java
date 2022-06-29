package test_project;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Test_enum {
	public static void main(String args[]){
		String s=VerifyStatus.UNKNOWN.getValue();
		System.out.println(s);
		System.out.println(VerifyStatus.VERIFIED.ordinal());
		System.out.println(VerifyStatus.getValueByCode(1));
	}
}


enum VerifyStatus
{
	UNKNOWN(1,"δ֪") {},
	NEW(2,"δ����") {},
	VERIFIED(3,"�������") {},
	E0(4,"�����") {},
	FINISH(5,"������") {},
	CHECKED(6,"Ԥ�����");
	
	private Integer code;
	private String value;

	private VerifyStatus(Integer code, String value)
	{
		this.code = code; 
		this.value = value;
	}
	
	
	private static final Map<Integer, VerifyStatus> enumMap = new HashMap<>();
	
	static {
        EnumSet<VerifyStatus> verifyStatusSet = EnumSet.allOf(VerifyStatus.class);
        for (VerifyStatus verifyStatus : verifyStatusSet) {
            enumMap.put(verifyStatus.getCode(), verifyStatus);
        }
    }
	
	
	public Integer getCode() {
		return code;
	}

	public String getValue()
	{
		return value;
	}

	public static String getValueByCode(Integer code) {
		return enumMap.get(code) == null ? null : enumMap.get(code).getValue();
	}
}