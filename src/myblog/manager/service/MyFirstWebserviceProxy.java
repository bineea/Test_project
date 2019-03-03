package myblog.manager.service;

public class MyFirstWebserviceProxy implements myblog.manager.service.MyFirstWebservice {
  private String _endpoint = null;
  private myblog.manager.service.MyFirstWebservice myFirstWebservice = null;
  
  public MyFirstWebserviceProxy() {
    _initMyFirstWebserviceProxy();
  }
  
  public MyFirstWebserviceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMyFirstWebserviceProxy();
  }
  
  private void _initMyFirstWebserviceProxy() {
    try {
      myFirstWebservice = (new myblog.manager.service.MyFirstWebserviceImplServiceLocator()).getMyFirstWebserviceImplPort();
      if (myFirstWebservice != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)myFirstWebservice)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)myFirstWebservice)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (myFirstWebservice != null)
      ((javax.xml.rpc.Stub)myFirstWebservice)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public myblog.manager.service.MyFirstWebservice getMyFirstWebservice() {
    if (myFirstWebservice == null)
      _initMyFirstWebserviceProxy();
    return myFirstWebservice;
  }
  
  public java.lang.String sayHello() throws java.rmi.RemoteException{
    if (myFirstWebservice == null)
      _initMyFirstWebserviceProxy();
    return myFirstWebservice.sayHello();
  }
  
  public myblog.manager.service.Role[] findRoleByName(java.lang.String arg0) throws java.rmi.RemoteException{
    if (myFirstWebservice == null)
      _initMyFirstWebserviceProxy();
    return myFirstWebservice.findRoleByName(arg0);
  }
  
  
}