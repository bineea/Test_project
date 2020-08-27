package test_project;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test_unsafe {

    private int x;
    private static Unsafe UNSAFE;

    static {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            UNSAFE = (Unsafe) unsafeField.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unsafe获取数据元素
     */
    public void getArrayObject() {
        Integer[] testInt = new Integer[] {1,2,3};
        int x_scale = UNSAFE.arrayIndexScale(testInt.getClass());
        long base_offset = UNSAFE.arrayBaseOffset(testInt.getClass());
        Integer second = (Integer) UNSAFE.getObject(testInt, (2 - 1) * x_scale + base_offset);
        System.out.println("testInt数组中的第二个元素为："+second);
    }
    
    /**
     * Unsafe实现cas操作
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    public void sampleCasOperate(Test_unsafe test_unsafe) throws NoSuchFieldException, SecurityException {
        long x_offset = UNSAFE.objectFieldOffset(test_unsafe.getClass().getDeclaredField("x"));
        int tmp = test_unsafe.x;
        UNSAFE.compareAndSwapInt(test_unsafe, x_offset, tmp, tmp+=1);
        System.out.println("x offset :"+x_offset);
        System.out.println("tmp:"+tmp);
    }
    
    /**
     * Unsafe实现具体cas操作
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    public void casOperate(Test_unsafe test_unsafe) throws NoSuchFieldException, SecurityException {
        //获取非静态属性Field在对象实例中的偏移量，读写对象的非静态属性时会用到这个偏移量。
        final long x_offset = UNSAFE.objectFieldOffset(test_unsafe.getClass().getDeclaredField("x"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                    	//对象的指定偏移地址处读取一个int值，并保证读取操作的可见性和有序性。
                        int tmp = UNSAFE.getIntVolatile(test_unsafe, x_offset);
                        
                        //比较对象的x_offset处内存位置中的值和期望的值，如果相同则更新。此更新是不可中断的。
                        if(UNSAFE.compareAndSwapInt(test_unsafe, x_offset, tmp, tmp+=1)) {
                            System.out.println("~~~1:"+tmp);
                        }
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                    	//对象的指定偏移地址处读取一个int值，并保证读取操作的可见性和有序性。
                        int tmp = UNSAFE.getInt(test_unsafe, x_offset);
                        
                        //比较对象的x_offset处内存位置中的值和期望的值，如果相同则更新。此更新是不可中断的。
                        if(UNSAFE.compareAndSwapInt(test_unsafe, x_offset, tmp, tmp+=1)) {
                            System.out.println("~~~2:"+tmp);
                        }
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws NoSuchFieldException {

        int j = 1;
        System.out.println(j << (31 - Integer.numberOfLeadingZeros(3)));
        System.out.println(j * 3);
        System.out.println(j << (31 - Integer.numberOfLeadingZeros(4)));
        System.out.println(j * 4);
        System.out.println(j << (31 - Integer.numberOfLeadingZeros(5)));
        System.out.println(j * 5);

        /*内部类
        final Test_unsafe test_unsafe = new Test_unsafe();
        class Test_inner {
            public void test_inner2outer() {
                System.out.println("test_inner2outer:" + test_unsafe.x);
            }
        }*/

        final Test_unsafe test_unsafe = new Test_unsafe();
        test_unsafe.getArrayObject();
        test_unsafe.sampleCasOperate(test_unsafe);
        test_unsafe.casOperate(test_unsafe);
    }

}
