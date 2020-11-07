package test_project.jvm.OOM;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//������������ʱ���������
//jdk1.8��ǰ��ͨ�����ô�ʵ�ַ������� -verbose:gc -XX:PermSize=20M -XX:MaxPermSize=20M
//jdk1.8���Ժ�ͨ��Ԫ�ռ�ʵ�ַ������� -verbose:gc -XX:MetaspaceSize=20M -XX:MaxMetaspaceSize=20M

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
			//Ĭ��UseCacheΪtrue����jvm����InstanceKlass��CGLib��̬��������������ظ���InstanceKlass
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
