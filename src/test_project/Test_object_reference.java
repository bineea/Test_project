package test_project;

//Java���ε����⣬�������ı�׼����Javaֻ��һ�ֲ������ݷ�ʽ���Ǿ��ǰ�ֵ���ݣ���Java�д����κζ������Ǵ�ֵ��
//�������βκ�ʵ�Σ����巽��ʱд�Ĳ������βΣ��������÷���ʱ�����ݵĲ�����ʵ�Ρ�
//���÷���ʱ�����ʵ�δ��ݸ��βΣ������ڲ���ʵ����ʹ���βΡ�
//��νֵ���ݾ��ǵ������ǻ�������ʱ�����ݲ������ǲ���ʵ�ʵ�ֵ�����紫��i=10����ʵ����ʱ����10��ֵ�����βΡ�
//�������Ƕ�������ʱ�����ݵ��Ƕ������õ�ֵ��Ҳ���Ƕ�����׵�ַ�����ǰѶ���ĵ�ַ��ֵ���βΡ�

public class Test_object_reference {
	
	//����Ϊ�������Ͷ���
	public static void testBasicType(int i) {
		System.out.println("���i��"+i);
		i+=50;
		System.out.println("���ִ��i+=50��i��"+i);
	}
	
	//����Ϊ���󣬲��ı�����
	public static void testBufferAppend(StringBuffer buffer) {
		buffer.append("+ buffer_append");
	}
	
	//����Ϊ���󣬸ı�����
	public static void testChangeBufferReference(StringBuffer buffer) {
		buffer = new StringBuffer("Java");
	}
	
	public static void main(String[] args) {
		int m = 50;
		testBasicType(m);
		System.out.println("���m��"+m);
		StringBuffer str = new StringBuffer("init_buffer");
		System.out.println("���str��"+str.toString());
		testBufferAppend(str);
		System.out.println("���ִ��testBufferAppend��str��"+str.toString());
		testChangeBufferReference(str);
		System.out.println("���ִ��testChangeBufferReference��str��"+str.toString());
	}
}
