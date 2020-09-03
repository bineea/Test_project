package test_project.exercise.other;

/**
 * 计算圆周率 
 * 
 * 边长为2r的正方形内嵌了一个以r为半径的圆。
 * InsideCircle面积 = 以r为半径的圆面积  = π*r*r
 * 正方形面积 = 2r*2r
 * 所以， InsideCircle面积 / 正方形面积 = (π*r*r) / (2r*2r) = π/4
 * 依据勾股定理，所有圆周上的点与直径两点的连线都会构成一个直角三角形！如果c*c <= 2r*2r，就表示该坐标位于圆内！
 * 因此如果有若干点均匀落入到正方形中，那么落入InsideCircle的点的个数占总数的比率也将会是π/4，由此将会统计出π的值。
 * 
 * @author bineea
 *
 */
public class Test_01_calculate_Pi {

	public static void main(String[] args) {
		Test_01_calculate_Pi test = new Test_01_calculate_Pi();
		System.out.println("Math.PI:"+Math.PI);
		test.calculatePi_RANDOM();
	}
	
	//半径为1d
	public void calculatePi_RANDOM() {
		
		double all=0d,yx=0d,d=0d;
		for(int i=0; i<1000000; i++) {
			double x = Math.random() * 2;
			double y = Math.random() * 2;
			if(y >= 1d) {
				d = (y-1) * (y-1) + x * x + (y-1) * (y-1) + (2-x) * (2-x);
			} else {
				d = (1-y) * (1-y) + x * x + (1-y) * (1-y) + (2-x) * (2-x);
			}
			
			if(d <= 4d) {
				yx+=1d;
			}
			all+=1d;
		}
		System.out.println(yx);
		System.out.println(all);
		System.out.println(yx / all * 4d);
	}
}
