package test_project.basic.concurrency;


/**
 * volatile伪共享不是说线程安全问题，而是代码执行效率优化问题
 * volatile关键字可以用来保证变量的可见性，它实现这个功能靠的是缓存一致性协议：一旦cpu修改了某个缓存行的数据，对于其他cpu而言，这个缓存行就失效了，只能从内存中重新加载。
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
    // 避免伪共享： @Contended +  jvm参数：-XX:-RestrictContended
    //@Contended
    volatile long x;
    //避免伪共享： 缓存行填充
    //long p1, p2, p3, p4, p5, p6, p7;
    volatile long y;
}