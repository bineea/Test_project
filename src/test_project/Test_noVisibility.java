package test_project;

public class Test_noVisibility {
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		public void run() {
			while(!ready){
				System.out.println("yield");
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().run();
		number = 12;
		ready = true;
	}
}
