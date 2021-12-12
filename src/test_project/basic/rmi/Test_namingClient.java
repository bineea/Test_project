package test_project.basic.rmi;

import java.rmi.Naming;

public class Test_namingClient {

	public static void main(String[] args) {
		Test_namingClient testNamingClient = new Test_namingClient();
		testNamingClient.testNamingClient();
	}

	public void testNamingClient() {
		try {
			String remoteAddr = "rmi://localhost:1099/EvilRemoteService";
			EvilRemoteService evilRemoteService = (EvilRemoteService) Naming.lookup(remoteAddr);
			String response = evilRemoteService.doEvil("test");
			System.out.println(response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
