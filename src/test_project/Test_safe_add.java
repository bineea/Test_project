package test_project;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Test_safe_add {

    public static AtomicInteger atomic = new AtomicInteger(0);
    private static final int thread_count = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] Threads = new Thread[thread_count];
        for (int i=0;i<thread_count;i++) {
            int num = i;
            Threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int a=0;a<1000;a++) {
                        atomic.incrementAndGet();
                        System.out.println("当前执行线程【"+ num +"】，累加次数【"+a+"】");
                    }
                }
            });
            Threads[i].start();
        }

        while(atomic.intValue() != 20000) {
            continue;
        }
        System.out.println(atomic);
    }

}
