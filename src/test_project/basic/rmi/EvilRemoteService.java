package test_project.basic.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ����̳�Remote�ӿڣ����ҷ��������׳� java.rmi.RemoteException �쳣
 * @author bineea
 *
 */
public interface EvilRemoteService extends Remote {

	String doEvil(Object... objects) throws RemoteException;
}
