package test_project.basic.concurrency;

public class Test_extends_thread extends Thread{

	@Override
	public void run() {
		System.out.println("ִ��run����");
		int i=0;
		while(true) {
			i++;
			System.out.println("��ǰi:"+i);
		}
	}

	public static void main(String[] args) {
		
		new Test_extends_thread().run();
		
//		new Test_extends_thread().start();
	}
}
