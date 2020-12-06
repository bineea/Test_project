package test_project.basic.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import test_project.basic.proxy.HelloworldImpl;

public class Test_reflex_forname {
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> proxyInterface = Class.forName("test_project.basic.proxy.Helloworld", true, Thread.currentThread().getContextClassLoader());
		if(proxyInterface.isInterface()) {
			System.out.println("proxyInterface == �ӿ�");
		} else {
			System.out.println("proxyInterface != �ӿ�");
		}
		
		Class<?> proxyClass = Class.forName("test_project.basic.proxy.HelloworldImpl", true, Thread.currentThread().getContextClassLoader());
		if(proxyClass.isInterface()) {
			System.out.println("proxyClass == �ӿ�");
		} else {
			System.out.println("proxyClass != �ӿ�");
		}
		Constructor<?> constructorClass = proxyClass.getConstructor();
		HelloworldImpl hellowordlmpl = (HelloworldImpl) constructorClass.newInstance();
		hellowordlmpl.sayHello();
	}

}
