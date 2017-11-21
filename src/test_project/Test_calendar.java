package test_project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test_calendar {
	public static void main(String args[]){
		Calendar c=Calendar.getInstance();
		c.set(2016,04,01);
		Calendar now=Calendar.getInstance();
		
		int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH);
		int day=now.get(Calendar.DATE);
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()));
		
		now.add(Calendar.MONTH, -6);
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()));
		System.out.println(Calendar.getInstance().get(Calendar.MONTH));
		
		if(c.after(now)){
			System.out.println("error");
		}
		else{
			System.out.println("success");
		}
		
		
		System.out.println("分割线=============================================分割线");
		String time = "2014/10/15";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
		Calendar t = Calendar.getInstance(); 
		try {
			t.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("报错！！！");
		}
		System.out.println(t.get(Calendar.YEAR));
		System.out.println(t.get(Calendar.MONTH));
		System.out.println(t.get(Calendar.DATE));
		
		System.out.println("分割线=============================================分割线");
		Calendar time1 = Calendar.getInstance();
		Calendar time2 = Calendar.getInstance();
		if(time1.before(time2))
			System.out.println("right");
		else
			System.out.println("!!!!");
		if(time2.before(time1))
			System.out.println("wrong");
		else
			System.out.println("!!!!");
		if(time1.compareTo(time2) == 0)
			System.out.println("=====");
		System.out.println("分割线=============================================分割线");
		
		SimpleDateFormat sdf123 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(sdf123.format(Calendar.getInstance().getTime()));
	}
}
