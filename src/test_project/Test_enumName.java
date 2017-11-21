package test_project;

public class Test_enumName {
	public static void main(String[] args)
	{
//		System.out.println(Life.CHILD.name());
//		System.out.println(Life.CHILD.getTime());
//		System.out.println(Life.CHILD.getColor());
		
		for(Life l:Life.values())
		{
			System.out.println(l.name());
			System.out.println(l.getTime());
			System.out.println(l.getColor());
		}
	}
	
	public enum Life
	{
		CHILD("童年","red"){},
		TEEN("少年","sky"){},
		YOUTH("青年","orange"){},
		MIDDLE("中年","write"){},
		OLD("老年","black"){},
		;
		
		private String time;
		private String color;
		
		private Life(String time, String color)
		{
			this.time = time;
			this.color = color;
		}
		
		public String getTime()
		{
			return time;
		}
		
		public String getColor()
		{
			return color;
		}
	}
}