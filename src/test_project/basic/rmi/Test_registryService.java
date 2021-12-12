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
			//���������ϵ�Զ�̶���ע���Registry��ʵ��,Ĭ�϶˿�1099
			Registry registry = LocateRegistry.createRegistry(1099);
			//����һ��Զ�̶���
			EvilRemoteService evilRemoteService = new EvilRemoteServiceImpl();
			//��Զ�̶���ע�ᵽRMIע��������ϣ�������ΪEvilRemoteService
			registry.bind("EvilRemoteService", evilRemoteService);
			//registry.rebind("EvilRemoteService", evilRemoteService);
			System.out.println("����RMI����ɹ�~~~");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
