package test_project.basic.concurrency;

public class Test_safe_double_check {

	private volatile static Test_safe_double_check instance;
	
	private Test_safe_double_check() {
		
	}
	
	public static Test_safe_double_check getInstance() {
		if (instance == null) {
			synchronized (Test_safe_double_check.class) {
				if (instance == null) {
					instance = new Test_safe_double_check();
				}
			}
		}
		return instance;
	}
}
