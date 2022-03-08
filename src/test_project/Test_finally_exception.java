package test_project;

/**
 * finally������д��ڡ�return������break�����������쳣��ʧ
 * @author guowenbin9
 *
 */
public class Test_finally_exception {

	public static void main(String[] args) throws Exception {
		Test_finally_exception test = new Test_finally_exception();
		test.testReturn();
		test.testBreak();
	}
	
	public void testReturn() {
		boolean result = true;
		try {
			throw new Exception("test");
		} catch(Exception ex) {
			result = false;
			throw ex;
		} finally {
			if (!result) {
				System.out.println("finallyִ�С�false���߼�����Ϊ���ڡ�return�����޷��׳�ex�쳣");
			} else {
				System.out.println("finallyִ�С�true���߼�����Ϊ���ڡ�return�����޷��׳�ex�쳣");
			}
			return;
		}
	}
	
	public void testBreak() {
		
		for(int i=0; i<10; i++) {
			boolean result = true;
			try {
				throw new Exception("test");
			} catch(Exception ex) {
				result = false;
				throw ex;
			} finally {
				if (!result) {
					System.out.println("finallyִ�С�false���߼�����Ϊ���ڡ�return�����޷��׳�ex�쳣");
				} else {
					System.out.println("finallyִ�С�true���߼�����Ϊ���ڡ�return�����޷��׳�ex�쳣");
				}
				break;
			}
		}

	}
}
