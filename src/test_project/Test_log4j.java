package test_project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log4j关于lookup使用文档
 * https://logging.apache.org/log4j/2.x/manual/lookups.html
 * 
 * @author bineea
 *
 */
public class Test_log4j {

	private static final Logger LOGGER;
	
	static {
		String path = "D:\\Project\\Java\\Test_project\\src\\test_project\\log4j2.properties";
		System.setProperty("log4j.configurationFile", path);
		LOGGER = LogManager.getLogger(Test_log4j.class);
	}
	
	public static void main(String[] args) {
		Test_log4j testLog = new Test_log4j();
		testLog.testJvmLog();
	}
	
	public void testJvmLog() {
		String logInfo = "${java.vm}";
		LOGGER.error("Test jvm info log: {}", logInfo);
	}
	
	public void testRmiLog() {
		String logInfo = "${java.vm}";
		LOGGER.error("Test jvm info log: {}", logInfo);
	}
}
