package test_project.jvm.OOM;

import java.util.ArrayList;
import java.util.List;

public class Test_oom_heap {
	
	static class OOMObject
	{
		
	}
	
	public static void main(String[] args)
	{
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true)
		{
			list.add(new OOMObject());
		}
	}

}
