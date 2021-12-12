package test_project.basic.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Test_namingService {

	public static void main(String[] args) {
		Test_namingService testNamingService = new Test_namingService();
		testNamingService.testNamingService();
	}
	
	public void testNamingService() {
		try {
            // 本地主机上的远程对象注册表Registry的实例
            LocateRegistry.createRegistry(1099);
            // 创建一个远程对象
            EvilRemoteService evilRemoteService = new EvilRemoteServiceImpl();
            // 把远程对象注册到RMI注册服务器上，并命名为Hello 
            //绑定的URL标准格式为：rmi://host:port/name
            Naming.bind("rmi://localhost:1099/EvilRemoteService", evilRemoteService);
            System.out.println("启动RMI服务成功~~~");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
