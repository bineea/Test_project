package test_project.jvm.OOM;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//方法区和运行时常量池溢出
//-verbose:gc -XX:PermSize=10M -XX:MaxPermSize=10M

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
