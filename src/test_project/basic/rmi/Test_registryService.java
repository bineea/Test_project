package test_project.basic.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Test_registryService {
	
	public static void main(String[] args) {
		Test_registryService testService = new Test_registryService();
		testService.testRegistryService();
	}
	
	public void testRegistryService() {
		try {
			//本地主机上的远程对象注册表Registry的实例,默认端口1099
			Registry registry = LocateRegistry.createRegistry(1099);
			//创建一个远程对象
			EvilRemoteService evilRemoteService = new EvilRemoteServiceImpl();
			//把远程对象注册到RMI注册服务器上，并命名为EvilRemoteService
			registry.bind("EvilRemoteService", evilRemoteService);
			//registry.rebind("EvilRemoteService", evilRemoteService);
			System.out.println("启动RMI服务成功~~~");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
