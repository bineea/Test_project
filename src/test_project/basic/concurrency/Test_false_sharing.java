package test_project.basic.concurrency;


/**
 * volatileα������˵�̰߳�ȫ���⣬���Ǵ���ִ��Ч���Ż�����
 * volatile�ؼ��ֿ���������֤�����Ŀɼ��ԣ���ʵ��������ܿ����ǻ���һ����Э�飺һ��cpu�޸���ĳ�������е����ݣ���������cpu���ԣ���������о�ʧЧ�ˣ�ֻ�ܴ��ڴ������¼��ء�
 * @author guowenbin9
 *
 */
public class Test_false_sharing {
	
    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer());
    }
    
    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - start);
    }
}
class Pointer {
    // ����α���� @Contended +  jvm������-XX:-RestrictContended
    //@Contended
    volatile long x;
    //����α���� ���������
    //long p1, p2, p3, p4, p5, p6, p7;
    volatile long y;
}