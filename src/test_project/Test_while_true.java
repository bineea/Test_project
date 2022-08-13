package test_project;

public class Test_while_true {
	
	public static void main(String[] args) {
		Test_while_true test = new Test_while_true();
		test.testWhileTrue();
		System.out.println("end");
	}
	
	public void testWhileTrue() {
		System.out.println("start");
		while(true) {
			System.out.println("in process");
		}
	}

}
