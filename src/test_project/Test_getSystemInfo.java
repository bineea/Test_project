package test_project;

import java.lang.management.ManagementFactory;

/**
 * Java实现获取cpu、内存、硬盘、网络等信息
 * @author bineea
 *
 */
public class Test_getSystemInfo {
	
	public void get() {
		ManagementFactory.getMemoryManagerMXBeans();
		ManagementFactory.getOperatingSystemMXBean();
	}
}
