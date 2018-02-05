package test_project;

public class Test_final {
	private final int orderNum = 1;
	private final Test t = new Test();
	
	public static void main(String[] args){
		Test_final tf = new Test_final();
		tf.t.setName("normal");
		tf.t.setValue("Õý³£");
		System.out.println("tf.orderNum£º"+tf.orderNum);
		System.out.println("tf.t.getName()£º"+tf.t.getName());
		System.out.println("tf.t.getValue()£º"+tf.t.getValue());
		tf.t.setName("failed");
		tf.t.setValue("Ê§°Ü");
		System.out.println("tf.t.getName()£º"+tf.t.getName());
		System.out.println("tf.t.getValue()£º"+tf.t.getValue());
		
		Test_final tf1 = new Test_final();
		System.out.println("tf1.orderNum£º"+tf1.orderNum);
		System.out.println("tf1.t.getName()£º"+tf1.t.getName());
		System.out.println("tf1.t.getValue()£º"+tf1.t.getValue());
	}

}

class Test {
	
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
