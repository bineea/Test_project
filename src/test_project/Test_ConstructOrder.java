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
        print("构造块");
    }
    static {
        print("静态块");
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
     * 尽管t1，t2都是对象，但是t1，t2，i，n都由static修饰，只会初始化一次！！！
     * 先初始化类变量然后赋值（按照类变量先后顺序执行），再初始化实例变量然后赋值（按照实例变量先后顺序）
     */
    public static void main(String[] args) {
        new Test_ConstructOrder("init");
    }
 
}