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
		//log4j配置文件绝对路径
		String path = "D:\\Project\\Java\\Test_project\\src\\test_project\\log4j2.properties";
		System.setProperty("log4j.configurationFile", path);
		//log4j2.formatMsgNoLookups 设置为true将关闭log4j2对lookups的支持，修改完配置请注意验证回归
		//配置JVM启动参数：-Dlog4j2.formatMsgNoLookups=true
		//log4j2.formatMsgNoLookups 参数官方说明：https://logging.apache.org/log4j/2.x/manual/configuration.html
		//log4j2.formatMsgNoLookups源码,标明仅在 >= 2.10.0 以上版本生效，默认值为false
		System.out.println(System.getProperties().getProperty("log4j2.formatMsgNoLookups"));
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
