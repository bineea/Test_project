package test_project;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class Test_string {
	public static void main(String[] args)
	{
		Test_string ts = new Test_string();
		ts.test_pattern();
	}
	
	public void test_trim()
	{
		String str = null;
		String sss = "";
//		System.out.println(str.trim());
		System.out.println("sss:"+sss.trim());
	}
	
	public void test_listTOstring()
	{
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("abc");
		list.add("ABC");
		String str = String.join(",", list);
		System.out.println("String.join:"+str);
		System.out.println("list.toString:"+list.toString());
		
		List<model> ml = new ArrayList<model>();
		for(model m:ml)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
	
	public void test_substring()
	{
		String str = "";
		System.out.println(str.substring(0, str.length()-1));
	}
	
	public void test_split()
	{
		String s="1,1,,1,,";
		String sss = "1,1,1,1,1,1";
		String[] o = s.split(",",7);
		String[] ooo = sss.split(",");
		StringTokenizer stk = new StringTokenizer(s, ",");
		String[] os = new String[6];
		int i = 0;
		while(stk.hasMoreTokens())
		{
			os[i] = stk.nextToken();
			System.out.println("数组中的字符为："+os[i]);
			i++;
		}
		System.out.println("sp数组中的字符为："+os[1]);
		System.out.println("sp数组中的字符为："+os[2]);
		System.out.println("sp数组中的字符为："+os[3]);
		
		for(String sstr:o)
		{
			if(sstr.isEmpty())
				System.out.println("输出为：空！！");
			System.out.println("输出为："+sstr);
		}
		System.out.println(o.length);
		System.out.println(ooo.length);
		System.out.println(o[0] == "1");
		System.out.println(o[0].equals("1"));
		System.out.println("1".equals(o[0].trim()));
		
		String str = "123,";
		String[] st = str.split(",");
		for(String ss:st)
		{
			System.out.println("~~~~~"+ss);
		}
	}
	
	public void test_null()
	{
		System.out.println("".isEmpty());
		System.out.println("" == null);
	}
	
	public void test_intValue()
	{
		String intValue = "";
		int i = Integer.valueOf(intValue).intValue();
		System.out.println("输出字符串对应数字为："+i);
	}
	
	public void test_buffer()
	{
		StringBuffer buffer = new StringBuffer();
		System.out.println("buffer中为："+buffer.toString());
		System.out.println(buffer.toString() == null);
		System.out.println(buffer.toString().equals(""));
		if(StringUtils.hasText(buffer.toString()))
			System.out.println("buffer不为空");
		else
			System.out.println("buffer为空");
		System.out.println("show:"+buffer.toString().replaceAll(",", ""));
	}
	
	public void test_list_contains()
	{
		List<String> lines = new ArrayList<String>();
		lines.add("1");
		lines.add("2");
		lines.add("3");
		lines.add("4");
		System.out.println(lines.get(0));
		System.out.println(lines.get(1));
		System.out.println(lines.contains(""));
		System.out.println(lines.contains(null));
	}
	
	public void test_contains()
	{
		String asd = "GAJGR11#GAJGE11#GAJGE12";
		if(asd.contains("GAJGE"))
			System.out.println("包含GAJGE");
		String dsa = null;
		System.out.println(dsa==null?asd.substring(0,asd.length()-1):"dsa");
		
		String ip = "127.0.0.1,192.168.60.47,1.202.219.75,1.202.219.21";
		if(ip.contains("1.202.219.21"))
			System.out.println("包含ip：1.202.219.21");
		else
			System.out.println("不包含ip：1.202.219.21，有误");
		
	}
	
	public void test_format()
	{
		String num = "123456789";
		System.out.println(String.format("%04d", Integer.parseInt(num)));
	}
	
	public void test_equals(String s)
	{
		if(!s.equals(null))
		{
			System.out.println("后 字符串为空");
		}
		String qwer = null;
		if(qwer.equals(s))
		{
			System.out.println("前 字符串为空");
		}
	}
	
	public void test_pattern()
	{
//		String number = "1.00";
//		Pattern pattern = Pattern.compile("^\\d{1,8}$");
		String number = "ssss123/.sdf";
		Pattern pattern = Pattern.compile("^[^\\\\:/\\*\\?\\\"<>\\|]+\\.{1,1}[^\\:/\\*\\?\\\"<>\\|]+$");
		Matcher matcher = pattern.matcher(number);
		if(matcher.matches())
		{
			System.out.println("匹配成功");
		}
		else
		{
			System.out.println("匹配失败");
		}
	}
}

class model{
	
	private String value;
	
	public model()
	{
		
	}
	
	public model(String value)
	{
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
