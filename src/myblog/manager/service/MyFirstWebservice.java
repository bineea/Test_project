/**
 * MyFirstWebservice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package myblog.manager.service;

public interface MyFirstWebservice extends java.rmi.Remote {
    public java.lang.String sayHello() throws java.rmi.RemoteException;
    public myblog.manager.service.Role[] findRoleByName(java.lang.String arg0) throws java.rmi.RemoteException;
}
