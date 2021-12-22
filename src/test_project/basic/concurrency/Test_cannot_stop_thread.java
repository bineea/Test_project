package test_project.basic.concurrency;

/**
 *
 * @author star
 */
public class Test_cannot_stop_thread {

    /**
     * main ������Ϊһ�����߳�
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // �����߳�
        myThread.start();

        // ���߳�ִ��
        for (; ; ) {
            if (myThread.isFlag()) {
                System.out.println("���̷߳��ʵ� flag ����");
                break;
            }
        }
    }

}

/**
 * ���߳���
 */
class MyThread extends Thread {

    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // �޸ı���ֵ
        flag = true;
        System.out.println("flag = " + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}