package test_project.basic.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Test_registryClient {

	public static void main(String[] args) {
		Test_registryClient testClient = new Test_registryClient();
		testClient.testRegistryClient();
	}
	
	public void testRegistryClient() {
		
		try {
			Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
			EvilRemoteService evilRemoteService = (EvilRemoteService) registry.lookup("EvilRemoteService");
			String reponse = evilRemoteService.doEvil("test");
			System.out.println(reponse);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
