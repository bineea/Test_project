package test_project.basic.sort;

/**
 * 插入排序：
 * 将待插元素，依次与已排序好的子数列元素从后到前进行比较，
 * 如果当前元素值比待插元素值大，则将移位到与其相邻的后一个位置，
 * 否则直接将待插元素插入当前元素相邻的后一位置，因为说明已经找到插入点的最终位置
 * 
 *
 * @author bineea
 *
 */

public class Test_insert_sort {
	
	public static void main(String[] args) {
		Test_insert_sort test = new Test_insert_sort();
		test.insertSort(new int[] {1,3,2,8,5,4});
	}

	public void insertSort(int[] num) {
		if(num == null || num.length < 2) {
			System.out.println("待排序数组无法排序，数组为空或者数组长度小于2");
			return;
		}
		//从第一个元素开始，可以认定第一个元素为已经被排序
		for(int i=1; i<num.length; i++) {
			int insertValue = num[i];
			//在前面有一个或连续多个值比insertValue大的时候,一直循环往前面找,将insertValue插入到这串值前面
			for(int x=i-1; x>=0; x--) {
				if(num[x] > insertValue) {
					num[x+1] = num[x];
					num[x] = insertValue;
				} else {
					break;
				}
			}
			
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
}
