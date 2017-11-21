package test_project;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test_time {
	public static void main(String[] args){
		Test_time t = new Test_time();
		t.compareTime();
	}
	
	public String compareTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		long now = Long.parseLong(sdf.format(Calendar.getInstance().getTime()));
		System.out.println(now);
		if(now > 73000)
			System.out.println("现在时间是7点半之后");
		if(now < 183000)
			System.out.println("现在时间是18点半之前");
		return null;
	}
}
