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
		test.testNormal();
	}
	
	public void testNormal() {
		boolean result = true;
		try {
			throw new Exception("test normal");
		} catch(Exception ex) {
			result = false;
			throw new RuntimeException(ex);
		} finally {
			if (!result) {
				System.out.println("finallyִ�С�false���߼���û�С�return������break���������׳�ex�쳣");
			} else {
				System.out.println("finallyִ�С�true���߼���û�С�return������break���������׳�ex�쳣");
			}
		}
	}
	
	public void testReturn() {
		boolean result = true;
		try {
			throw new Exception("test return");
		} catch(Exception ex) {
			result = false;
			throw new RuntimeException(ex);
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
				throw new Exception("test break");
			} catch(Exception ex) {
				result = false;
				throw new RuntimeException(ex);
			} finally {
				if (!result) {
					System.out.println("finallyִ�С�false���߼�����Ϊ���ڡ�break�����޷��׳�ex�쳣");
				} else {
					System.out.println("finallyִ�С�true���߼�����Ϊ���ڡ�break�����޷��׳�ex�쳣");
				}
				break;
			}
		}

	}
}
