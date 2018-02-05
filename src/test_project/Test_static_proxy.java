package test_project;

public class Test_static_proxy {

	public static void main(String[] args)
	{
		testProxy(new ProxyObject());
	}
	
	public static void testProxy(ProxyInterface pi)
	{
		pi.info();
	}
}

interface ProxyInterface{
	void info();
}

class RealProxy implements ProxyInterface{

	@Override
	public void info() {
		System.out.println("成功执行info方法");
	}
	
}

class ProxyObject implements ProxyInterface{

	@Override
	public void info() {
		System.out.println("代理类执行开始");
		RealProxy rp = new RealProxy();
		rp.info();
		System.out.println("代理类执行结束");
	}
	
}