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
		System.out.println("�ɹ�ִ��info����");
	}
	
}

class ProxyObject implements ProxyInterface{

	@Override
	public void info() {
		System.out.println("������ִ�п�ʼ");
		RealProxy rp = new RealProxy();
		rp.info();
		System.out.println("������ִ�н���");
	}
	
}