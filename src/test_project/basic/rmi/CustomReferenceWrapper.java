package test_project.basic.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.naming.NamingException;
import javax.naming.Reference;

public class CustomReferenceWrapper extends UnicastRemoteObject implements CustomRemoteReference{
    protected Reference wrappee;
    private static final long serialVersionUID = 6078186197417641456L;
 
    public CustomReferenceWrapper(Reference reference) throws NamingException, RemoteException {
        this.wrappee = reference;
    }
 
    @Override
    public Reference getReference() throws RemoteException {
        return this.wrappee;
    }

}
