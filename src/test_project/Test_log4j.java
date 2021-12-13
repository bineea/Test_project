package test_project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log4j����lookupʹ���ĵ�
 * https://logging.apache.org/log4j/2.x/manual/lookups.html
 * 
 * @author bineea
 *
 */
public class Test_log4j {

	private static final Logger LOGGER;
	
	static {
		//log4j�����ļ�����·��
		String path = "D:\\Project\\Java\\Test_project\\src\\test_project\\log4j2.properties";
		System.setProperty("log4j.configurationFile", path);
		//log4j2.formatMsgNoLookups ����Ϊtrue���ر�log4j2��lookups��֧�֣��޸���������ע����֤�ع�
		//����JVM����������-Dlog4j2.formatMsgNoLookups=true
		//log4j2.formatMsgNoLookups �����ٷ�˵����https://logging.apache.org/log4j/2.x/manual/configuration.html
		//log4j2.formatMsgNoLookupsԴ��,�������� >= 2.10.0 ���ϰ汾��Ч��Ĭ��ֵΪfalse
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
