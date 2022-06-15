package test_project;

/**
 * finally代码块中存在“return”、“break”，将导致异常丢失
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
				System.out.println("finally执行“false”逻辑，没有“return”、“break”，正常抛出ex异常");
			} else {
				System.out.println("finally执行“true”逻辑，没有“return”、“break”，正常抛出ex异常");
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
				System.out.println("finally执行“false”逻辑，因为存在“return”，无法抛出ex异常");
			} else {
				System.out.println("finally执行“true”逻辑，因为存在“return”，无法抛出ex异常");
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
					System.out.println("finally执行“false”逻辑，因为存在“break”，无法抛出ex异常");
				} else {
					System.out.println("finally执行“true”逻辑，因为存在“break”，无法抛出ex异常");
				}
				break;
			}
		}

	}
}
