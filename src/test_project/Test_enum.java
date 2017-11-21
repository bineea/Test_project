package test_project;

public class Test_enum {
	public static void main(String args[]){
		String s=VerifyStatus.UNKNOWN.getValue();
		System.out.println(VerifyStatus.VERIFIED.ordinal());
	}
}


enum VerifyStatus
{
	UNKNOWN("δ֪") {},
	NEW("δ����") {},
	VERIFIED("�������") {},
	E0("�����") {},
	FINISH("������") {},
	CHECKED("Ԥ�����");
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