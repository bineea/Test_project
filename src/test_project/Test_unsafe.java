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
     * Unsafe��ȡ����Ԫ��
     */
    public void getArrayObject() {
        Integer[] testInt = new Integer[] {1,2,3};
        int x_scale = UNSAFE.arrayIndexScale(testInt.getClass());
        long base_offset = UNSAFE.arrayBaseOffset(testInt.getClass());
        Integer second = (Integer) UNSAFE.getObject(testInt, (2 - 1) * x_scale + base_offset);
        System.out.println("testInt�����еĵڶ���Ԫ��Ϊ��"+second);
    }
    
    /**
     * Unsafeʵ��cas����
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
     * Unsafeʵ�־���cas����
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    public void casOperate(Test_unsafe test_unsafe) throws NoSuchFieldException, SecurityException {
        //��ȡ�Ǿ�̬����Field�ڶ���ʵ���е�ƫ��������д����ķǾ�̬����ʱ���õ����ƫ������
        final long x_offset = UNSAFE.objectFieldOffset(test_unsafe.getClass().getDeclaredField("x"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                    	//�����ָ��ƫ�Ƶ�ַ����ȡһ��intֵ������֤��ȡ�����Ŀɼ��Ժ������ԡ�
                        int tmp = UNSAFE.getIntVolatile(test_unsafe, x_offset);
                        
                        //�Ƚ϶����x_offset���ڴ�λ���е�ֵ��������ֵ�������ͬ����¡��˸����ǲ����жϵġ�
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
                    	//�����ָ��ƫ�Ƶ�ַ����ȡһ��intֵ������֤��ȡ�����Ŀɼ��Ժ������ԡ�
                        int tmp = UNSAFE.getInt(test_unsafe, x_offset);
                        
                        //�Ƚ϶����x_offset���ڴ�λ���е�ֵ��������ֵ�������ͬ����¡��˸����ǲ����жϵġ�
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

        /*�ڲ���
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
