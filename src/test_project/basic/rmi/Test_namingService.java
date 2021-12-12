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
            // ���������ϵ�Զ�̶���ע���Registry��ʵ��
            LocateRegistry.createRegistry(1099);
            // ����һ��Զ�̶���
            EvilRemoteService evilRemoteService = new EvilRemoteServiceImpl();
            // ��Զ�̶���ע�ᵽRMIע��������ϣ�������ΪHello 
            //�󶨵�URL��׼��ʽΪ��rmi://host:port/name
            Naming.bind("rmi://localhost:1099/EvilRemoteService", evilRemoteService);
            System.out.println("����RMI����ɹ�~~~");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
