package test_project;

//Java传参的问题，并且它的标准答案是Java只有一种参数传递方式：那就是按值传递，即Java中传递任何东西都是传值。
//参数有形参和实参，定义方法时写的参数叫形参，真正调用方法时，传递的参数叫实参。
//调用方法时，会把实参传递给形参，方法内部其实是在使用形参。
//所谓值传递就是当参数是基本类型时，传递参数就是参数实际的值，比如传递i=10，真实传参时，把10赋值给了形参。
//当参数是对象引用时，传递的是对象引用的值，也就是对象的首地址。就是把对象的地址赋值给形参。

public class Test_object_reference {
	
	//参数为基本类型对象
	public static void testBasicType(int i) {
		System.out.println("输出i："+i);
		i+=50;
		System.out.println("输出执行i+=50后i："+i);
	}
	
	//参数为对象，不改变引用
	public static void testBufferAppend(StringBuffer buffer) {
		buffer.append("+ buffer_append");
	}
	
	//参数为对象，改变引用
	public static void testChangeBufferReference(StringBuffer buffer) {
		buffer = new StringBuffer("Java");
	}
	
	public static void main(String[] args) {
		int m = 50;
		testBasicType(m);
		System.out.println("输出m："+m);
		StringBuffer str = new StringBuffer("init_buffer");
		System.out.println("输出str："+str.toString());
		testBufferAppend(str);
		System.out.println("输出执行testBufferAppend后str："+str.toString());
		testChangeBufferReference(str);
		System.out.println("输出执行testChangeBufferReference后str："+str.toString());
	}
}
