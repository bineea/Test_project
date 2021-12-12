package test_project.basic.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.naming.NamingException;
import javax.naming.Reference;

public interface CustomRemoteReference extends Remote{
	Reference getReference() throws NamingException, RemoteException;
}
