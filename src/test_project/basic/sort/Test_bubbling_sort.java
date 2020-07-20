package test_project.basic.sort;

/**
 * 冒泡排序：
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个；
 * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数； 
 * 针对所有的元素重复以上的步骤，除了最后一个；
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * @author bineea
 *
 */
public class Test_bubbling_sort {
	
	public static void main(String[] args) {
		Test_bubbling_sort test = new Test_bubbling_sort();
		test.bubblingSort(new int[] {1,3,2,8,5,4});
	}

	public void bubblingSort(int[] num) {
		//第一层for循环控制冒泡次数，每次冒泡将出现当前最大的数值，长度为n的数组需要n-1个最大的数值，所以循环为0~n-2共n-1次
		for(int x=0; x<num.length-1; x++) {
			//第二层for循环比对相邻的两个数值；第二层循环不再循环到arr.length - 1，因为外面的x循环递增一次,说明数组最后就多了一个排好序的大泡泡
			for(int i=0; i<num.length-1-x; i++) {
				int initValue = num[i];
				if(initValue > num[i+1]) {
					num[i] = num[i+1];
					num[i+1] = initValue;
				}
			}
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
}
