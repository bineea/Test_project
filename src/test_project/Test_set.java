package test_project;

import java.util.Set;
import java.util.TreeSet;

public class Test_set {

	public static void main(String[] args) {
		Test_set test = new Test_set();
		test.testTreeSet();
	}
	
	
	private void testTreeSet() {
		
		Set<String> treeSet = new TreeSet<>();
		treeSet.add("9");
		treeSet.add("a");
		treeSet.add("s");
		treeSet.add("e");
		treeSet.add("d");
		treeSet.add("b");
		
		treeSet.add("9");
		treeSet.add("9");
		treeSet.add("9");
		treeSet.add("b");
		treeSet.add("b");
		
		System.out.println("��ȡset�е�Ԫ�أ�"+treeSet.stream().findAny().orElse(null));
		
		for (String str : treeSet) {
			System.out.println("����"+str);
		}
	}
}
