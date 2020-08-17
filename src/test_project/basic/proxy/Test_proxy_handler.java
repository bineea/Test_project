package test_project.basic.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK��̬����
 * @author bineea
 *
 */
public class Test_proxy_handler {

	public static void main(String[] args) {
		Test_proxy_handler test = new Test_proxy_handler();
		Helloworld target = new HelloworldImpl();
		Helloworld hi_np = (Helloworld) test.proxyNewProxyInstanceHandler(target);
		hi_np.sayHello();
		Helloworld hi_ni = (Helloworld) test.proxyNewIntenceHandler(target);
		hi_ni.sayHello();
	}
	
	public void defaultProxyHandler() {
//		Helloworld hellow = new HelloworldImpl();
//		Helloworld hi = (Helloworld) Proxy.newProxyInstance(
//				Helloworld.class.getClassLoader(), hellow.getClass().getInterfaces(),
//				new InvocationHandler() {
//					
//					@Override
//					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//						// TODO Auto-generated method stub
//						return null;
//					}
//					
//				});
//		Helloworld hi = (Helloworld) Proxy.newProxyInstance(
//				Helloworld.class.getClassLoader(), HelloworldImpl.class.getInterfaces(),
//				new InvocationHandler() {
//
//					@Override
//					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//						// TODO Auto-generated method stub
//						return null;
//					}
//					
//				});
//		Helloworld hi = (Helloworld) Proxy.newProxyInstance(
//				Helloworld.class.getClassLoader(), new Class[] { Helloworld.class },
//				(proxyObj, method, args) -> {
//					// TODO Auto-generated method stub
//					return null;
//				});
		Helloworld hi = (Helloworld) Proxy.newProxyInstance(
				Helloworld.class.getClassLoader(), new Class[] { Helloworld.class },
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						return null;
					}
					
				});
		hi.sayHello();
		System.out.println(Helloworld.class.getInterfaces().length);
	}
	
	public Object proxyNewProxyInstanceHandler(Object target) {
		/*
		  * ���ش������ java.lang.Class ���� 
		  * ����1-loader���������������� 
		  * ����2-interfaces��������Ҫʵ�ֵĽӿ��б�
		 * ��interfaces �����е����� Class ��������ʾ�ӿڣ������ܱ�ʾ���������ͣ� 
		 * ��interfaces �����е�����Ԫ�ز�������ͬһClass ���� 
		 * �����нӿ����͵�����ͨ���ض��������������ɼ����仰˵������������� cl �����нӿ� i�����±��ʽ����Ϊ true��Class.forName(i.getName(), false, cl) == i�� 
		 * �����зǹ����ӿڱ���λ��ͬһ���У�
		  * ����3-h��ָ�ɷ������õĵ��ô������
		 */
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// ����ִ��Ŀ�����ķ���
						Object result = method.invoke(target, args);
						// ����Ŀ����󷽷���ִ�н��
						return result;
					}
				});
		return proxy;
	}
	
	public Object proxyNewIntenceHandler(Object target) {
		try {
			/*
			 * ���ش������ java.lang.Class ���� ����1-loader���������������� ����2-interfaces��������Ҫʵ�ֵĽӿ��б�
			 * ��interfaces �����е����� Class ��������ʾ�ӿڣ������ܱ�ʾ���������ͣ� ��interfaces �����е�����Ԫ�ز�������ͬһ
			 * Class ���� �����нӿ����͵�����ͨ���ض��������������ɼ����仰˵������������� cl �����нӿ� i�����±��ʽ����Ϊ true��
			 * Class.forName(i.getName(), false, cl) == i�� �����зǹ����ӿڱ���λ��ͬһ���У�
			 */
			@SuppressWarnings("deprecation")
			Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(),
					target.getClass().getInterfaces());
			// ����һ�� Constructor ��������ӳ�Ĺ��췽���Ǵ� Class ��������ʾ����Ĺ������췽�������β������� parameterTypes
			// ��ָ���Ĳ���������ƥ�䡣
			Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
			// ʹ�ô� Constructor �����ʾ�Ĺ��췽���������ù��췽�������������ʵ��������ָ���ĳ�ʼ��������ʼ����ʵ����
			// ����������Զ��������ƥ������βΣ���Ҫʱ���������������ò�����Ҫ���з�������ת����
			Object proxy = constructor.newInstance(new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// ����ִ��Ŀ�����ķ���
					Object result = method.invoke(target, args);
					// ����Ŀ����󷽷���ִ�н��
					return result;
				}
			});
			return proxy;
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
