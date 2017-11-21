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
		CHILD("ͯ��","red"){},
		TEEN("����","sky"){},
		YOUTH("����","orange"){},
		MIDDLE("����","write"){},
		OLD("����","black"){},
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