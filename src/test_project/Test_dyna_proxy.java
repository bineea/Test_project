package test_project;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test_dyna_proxy {

	public static void main(String[] args)
	{
		RealObject real = new RealObject();
		ObjectInterface proxy = (ObjectInterface)Proxy.newProxyInstance(ObjectInterface.class.getClassLoader(),new Class[]{ObjectInterface.class}, new DynaProxyObject(real));
		testProxy(proxy);
	}
	
	public static void testProxy(ObjectInterface pi)
	{
		pi.info();
	}
}

interface ObjectInterface{
    void info();
}

//被代理类
class RealObject implements ObjectInterface{
    public void info(){
        System.out.println("成功执行info方法");
    }
}

//代理类，实现InvocationHandler 接口
class DynaProxyObject implements InvocationHandler{
    private Object proxied = null;
    public DynaProxyObject(){
        
    }
    public DynaProxyObject(Object proxied){
        this.proxied  = proxied;
    }
    public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
        System.out.println("hello");
        return arg1.invoke(proxied, arg2);
    };
}
