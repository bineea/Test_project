package test_project;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test_queue {
	public static void main(String[] args) {
		Test_queue queue = new Test_queue();
		queue.test_BlockingQueue();
		System.out.println("hello world");
	}
	
	public void test_BlockingQueue()
	{
		 try 
		 {
			 BlockingQueue<String> queue=new ArrayBlockingQueue<String>(2);  
             queue.put("hello");  
             queue.put("world");  
             queue.put("yes");  
             System.out.println("yes"); 
             Thread.sleep(1000);
	     } 
		 catch (InterruptedException e) 
		 {  
	         e.printStackTrace();  
	     } 
	}
}
