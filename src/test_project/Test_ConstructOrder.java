package test_project;

public class Test_ConstructOrder {
	 
    public static int k = 0;
    public static int k1;
    public int k2;
    public static Test_ConstructOrder t1 = new Test_ConstructOrder("t1");
    public static Test_ConstructOrder t2 = new Test_ConstructOrder("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");
    {
        print("�����");
    }
    static {
        print("��̬��");
    }
 
    public Test_ConstructOrder(String str) {
        System.out.println((++k) + ":" + str + "    i=" + i + "    n=" + n);
        ++i;
        ++n;
    }
 
    public static int print(String str) {
        System.out.println((++k) + ":" + str + "    i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }
 
    /**
     * ����t1��t2���Ƕ��󣬵���t1��t2��i��n����static���Σ�ֻ���ʼ��һ�Σ�����
     * �ȳ�ʼ�������Ȼ��ֵ������������Ⱥ�˳��ִ�У����ٳ�ʼ��ʵ������Ȼ��ֵ������ʵ�������Ⱥ�˳��
     */
    public static void main(String[] args) {
        new Test_ConstructOrder("init");
    }
 
}