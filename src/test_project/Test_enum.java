package test_project;

public class Test_enum {
	public static void main(String args[]){
		String s=VerifyStatus.UNKNOWN.getValue();
		System.out.println(VerifyStatus.VERIFIED.ordinal());
	}
}


enum VerifyStatus
{
	UNKNOWN("未知") {},
	NEW("未送审") {},
	VERIFIED("处理完成") {},
	E0("审核中") {},
	FINISH("审核完成") {},
	CHECKED("预审完成");
	private String value;

	private VerifyStatus(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}