package test_project.basic.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
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
		  * 返回代理类的 java.lang.Class 对象 
		  * 参数1-loader：代理类的类加载器 
		  * 参数2-interfaces：代理类要实现的接口列表
		 * ·interfaces 数组中的所有 Class 对象必须表示接口，而不能表示类或基本类型； 
		 * ·interfaces 数组中的两个元素不能引用同一Class 对象； 
		 * ·所有接口类型的名称通过特定的类加载器必须可见换句话说，对于类加载器 cl 和所有接口 i，以下表达式必须为 true：Class.forName(i.getName(), false, cl) == i； 
		 * ·所有非公共接口必须位于同一包中；
		  * 参数3-h：指派方法调用的调用处理程序
		 */
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// 发射执行目标对象的方法
						Object result = method.invoke(target, args);
						// 返回目标对象方法的执行结果
						return result;
					}
				});
		return proxy;
	}
	
	public Object proxyNewIntenceHandler(Object target) {
		try {
			/*
			 * 返回代理类的 java.lang.Class 对象 参数1-loader：代理类的类加载器 参数2-interfaces：代理类要实现的接口列表
			 * ·interfaces 数组中的所有 Class 对象必须表示接口，而不能表示类或基本类型； ·interfaces 数组中的两个元素不能引用同一
			 * Class 对象； ·所有接口类型的名称通过特定的类加载器必须可见换句话说，对于类加载器 cl 和所有接口 i，以下表达式必须为 true：
			 * Class.forName(i.getName(), false, cl) == i； ·所有非公共接口必须位于同一包中；
			 */
			@SuppressWarnings("deprecation")
			Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(),
					target.getClass().getInterfaces());
			// 返回一个 Constructor 对象，它反映的构造方法是此 Class 对象所表示的类的公共构造方法，其形参类型与 parameterTypes
			// 所指定的参数类型相匹配。
			Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
			// 使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
			// 个别参数会自动解包，以匹配基本形参，必要时，基本参数和引用参数都要进行方法调用转换。
			Object proxy = constructor.newInstance(new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					// 发射执行目标对象的方法
					Object result = method.invoke(target, args);
					// 返回目标对象方法的执行结果
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
