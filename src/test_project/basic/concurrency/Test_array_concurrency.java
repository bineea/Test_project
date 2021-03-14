package test_project.basic.concurrency;

import java.util.concurrent.CopyOnWriteArrayList;

public class Test_array_concurrency {
	
	public static void main(String[] args) {
		//Test_array_concurrency test = new Test_array_concurrency();
		//List<String> list = Collections.synchronizedList(new ArrayList<>());
		//List<String> list = new ArrayList<String>();
		//list.add("");
		//Map<String, String> map = new ConcurrentHashMap<String, String>();
		//map.put("key", "value");
		
		//ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(0, 0, 0, null, null);
		//myExecutor.execute(new Runnable() {
		//	@Override
		//	public void run() {
		//		// TODO Auto-generated method stub
		//	}
		//});
		//for(初始化表达式;循环条件;操作表达式)
		//for(int i=0;;) {
		//	System.out.println("~~~~~~~~"+ i++);
		//}
		
		
		//Executors.newCachedThreadPool();
		
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		list.add("");
	}
}
