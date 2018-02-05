package test_project;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test_reflex {

	 @SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception{
	        //����A�Ĺ��췽��
	        Constructor c = A.class.getConstructor();
	        //����A�������Ϊpublic �����Ĺ��췽��
	        Constructor[] cons = A.class.getConstructors();
	        //����A�����еĹ��췽��������private
	        Constructor[] cons2 = A.class.getDeclaredConstructors();
	        //����A��ĵ�һ��public ����
	        Method m = A.class.getMethod("say");
	        //ִ��
	        m.invoke(A.class.newInstance(), null);
	        //����A�����е�public ����
	        Method[] ms = A.class.getMethods();
	        //����A�����еķ���������private
	        Method[] allMs = A.class.getDeclaredMethods();
	        //����A���public�ֶ�
	        Field field = A.class.getField("i");
	        System.out.println(field.get(A.class.newInstance()));
	        //����A���static �ֶ�
	        System.out.println(field.get(null));
	    }
}

class A{
    public int i = 1;
    public static int b = 2;
    public A(){
        System.out.println("�޲ι���");
    }
    private A(String s){
        System.out.println("�вι���"+s);
    }
    
    public void say(){
        System.out.println("say");
    }
}
