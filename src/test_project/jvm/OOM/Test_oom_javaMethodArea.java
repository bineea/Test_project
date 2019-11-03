package test_project.jvm.OOM;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Test_oom_javaMethodArea {
	
	public static void main(String[] args)
	{
		while(true)
		{
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
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
