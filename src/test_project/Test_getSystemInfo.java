package test_project;

import java.lang.management.ManagementFactory;

/**
 * Javaʵ�ֻ�ȡcpu���ڴ桢Ӳ�̡��������Ϣ
 * @author bineea
 *
 */
public class Test_getSystemInfo {
	
	public void get() {
		ManagementFactory.getMemoryManagerMXBeans();
		ManagementFactory.getOperatingSystemMXBean();
	}
}
