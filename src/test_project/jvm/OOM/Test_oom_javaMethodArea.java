package test_project.jvm.OOM;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//方法区和运行时常量池溢出
//jdk1.8以前，通过永久代实现方法区： -verbose:gc -XX:PermSize=20M -XX:MaxPermSize=20M
//jdk1.8及以后，通过元空间实现方法区： -verbose:gc -XX:MetaspaceSize=20M -XX:MaxMetaspaceSize=20M

public class Test_oom_javaMethodArea {
	
	public static void main(String[] args)
	{
		while(true)
		{
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			//默认UseCache为true，即jvm缓存InstanceKlass，CGLib动态代理不会大量创建重复的InstanceKlass
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {

				@Override
				public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy)
						throws Throwable {
					return methodProxy.invokeSuper(object, objects);
				}
				
			});
			enhancer.create();
		}
	}

	static class OOMObject
	{
		
	}
}
