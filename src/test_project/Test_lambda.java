package test_project;

import java.util.Arrays;
import java.util.List;

public class Test_lambda {
//	public static void main(String[] args)
//	{
//		String[] atp = { "test一", "test二", "test三", "test四", "test5",
//				"test6", "test7", "test8" };
//		List<String> players = Arrays.asList(atp);
//
//		// 以前的循环方式
//		for (String player : players) {
//			System.out.print(player + "; ");
//		}
//		System.out.println();
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//		// 使用 lambda 表达式以及函数操作(functional operation)
//		players.forEach((x) -> System.out.print(x + "; "));
//		System.out.println();
//		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//		// 在 Java 8 中使用双冒号操作符(double colon operator)
//		players.forEach(System.out::print);
//		
//		players.forEach(Consumer);
//		
//		Runnable race1 = new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("线程一");
//			}
//		};
//		
//		Runnable race2 = () -> System.out.println("线程二");;
//	}
	
}

class Person
{
	String name;
	Integer age;
	
	public Person(String name , Integer age)
	{
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public static boolean testPerson()
	{
		int i = (int) (Math.random()*10);
		return (i%2 == 0);
	}
}