package test_project;

import java.util.ArrayList;
import java.util.List;

public class Test_ergodic {
	@SuppressWarnings("unused")
	public static void main(String args[]){
		List<sss> str = new ArrayList<sss>();
		boolean i = true;
		for(sss s:str){
			i=false;
			System.out.println(s);
		}
		if(str.isEmpty()){
			System.out.println("!11");
		}
	}
}


class sss{
	String s;
	String n;
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
}