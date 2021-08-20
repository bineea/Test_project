package test_project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Test_LocalDataTime {
	public static void main(String[] args){
		Test_LocalDataTime test = new Test_LocalDataTime();
		test.handleString2LocalDateTime();
	}

	public void handleString2LocalDateTime() {
		String time1 = "2016-10-24";
		String time2 = "2017-10-24 14:40:54";
		String time3 = "0000";
		String time4 = "12:21:21";
		
		LocalDateTime dateTime = LocalDateTime.now();
		LocalTime time = LocalTime.now();
		LocalDate dateTime1 = LocalDate.parse(time1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if(dateTime1.isBefore(dateTime1))
			System.out.println("time1在当前时间之前");
		else
			System.out.println("time1在当前时间之后");
		String s1 = dateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDateTime dateTime2 = LocalDateTime.parse(time2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		if(dateTime2.isBefore(dateTime))
			System.out.println("time2在当前时间之前");
		else
			System.out.println("time2在当前时间之后");
		String s2 = dateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalTime dateTime3 = LocalTime.parse(time3, DateTimeFormatter.ofPattern("HHmm"));
		if(dateTime3.isBefore(time))
			System.out.println("time3在当前时间之前");
		else
			System.out.println("time3在当前时间之后");
		String s3 = dateTime3.format(DateTimeFormatter.ofPattern("HHmm"));
		LocalTime dateTime4 = LocalTime.parse(time4, DateTimeFormatter.ofPattern("HH:mm:ss"));
		if(dateTime4.isBefore(time))
			System.out.println("time4在当前时间之前");
		else
			System.out.println("time4在当前时间之后");
		String s4 = dateTime4.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		
		
		System.out.println(dateTime1.getDayOfMonth());
		System.out.println(dateTime1.toString()+"~~~~~");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		if(dateTime2.isAfter(dateTime))
			System.out.println("dateTime2时间在dateTime之后！！！");
		
		String date = "2016年09月22日";
		try
		{
			LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
			System.out.println("localDate为："+d.getMonth());
			System.out.println(LocalDate.now().toString()+"~~~~~");
			System.out.println(LocalDateTime.now().toString()+"~~~~~");
		}
		catch(DateTimeParseException e)
		{
			System.out.println("wrong"+e.getMessage());
		}
		
		LocalDateTime dateTime5 = LocalDateTime.now();
		String s5 = dateTime5.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH));
		System.out.println("英文格式时间为："+s5);
		String s6 = dateTime5.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS", Locale.ENGLISH));
		System.out.println("英文格式时间为："+s6);
		String s7 = dateTime5.format(DateTimeFormatter.ofPattern("MMM", Locale.CHINA));
		System.out.println("英文格式时间为："+s7);
		
		String q = "A1";
		if(q.replaceAll(",", "").isEmpty())
		{
			System.out.println("ok");
		}
		String w = null;
		if(w == null)
			System.out.println("null为空！");
	}
	
}
