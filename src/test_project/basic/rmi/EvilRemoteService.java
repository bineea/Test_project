package test_project.basic.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 必须继承Remote接口，并且方法必须抛出 java.rmi.RemoteException 异常
 * @author bineea
 *
 */
public interface EvilRemoteService extends Remote {

	String doEvil(Object... objects) throws RemoteException;
}
