package test_project.basic.sort;

/**
 * 快速排序：
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * 
 * 从数列中挑出一个元素（通常选用数列的第一个元素），称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归的（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * 
 * @author bineea
 *
 */
public class Test_quick_sort {
	
	public static void main(String[] args) {
		Test_quick_sort test = new Test_quick_sort();
		test.quickSort(new int[] {1,3,2,8,5,4}, 0, 6);
	}
	
	public void quickSort(int[] num, int start, int length) {
		if(num == null || num.length < 2 || length < 2) {
			System.out.println("待排序数组无法排序，数组为空或者数组长度小于2");
			return;
		}
		int pivot = num[start];
		int pivotIndex = start;
		for(int x=1; x<length; x++) {
			if(pivot > num[start+x]) {
				num[pivotIndex] = num[start+x];
				num[start+x] = pivot;
				pivotIndex = start+x;
			} 
		}
		
		quickSort(num, start, pivotIndex-start+1);
		quickSort(num, pivotIndex+1, length-pivotIndex+start-1);
		
		if(num.length == length) {
			for(int i=0; i<num.length; i++) {
				System.out.print(num[i] + ";");
			}
		}
	}
}
