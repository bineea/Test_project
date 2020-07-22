package test_project.basic.sort;

/**
 * 选择排序：
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。 
 * 
 * 初始状态：无序区为R[1..n]，有序区为空；
 * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 * 则n-1趟结束，数组有序化了。
 * 
 * @author bineea
 *
 */
public class Test_choice_sort {

	public static void main(String[] args) {
		Test_choice_sort test = new Test_choice_sort();
		test.choiceSort(new int[] {1,3,2,8,5,4});
	}
	
	public void choiceSort(int[] num) {
		if(num == null || num.length < 2) {
			System.out.println("待排序数组无法排序，数组为空或者数组长度小于2");
			return;
		}
		
		for(int i=0; i<num.length-1; i++) {
			int temp = num[i];
			int currentMinIndex = i;
			for(int x=i; x<num.length; x++) {
				if(num[x] < num[currentMinIndex]) {
					currentMinIndex = x;
				}
			}
			num[i] = num[currentMinIndex];
			num[currentMinIndex] = temp;
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
}
