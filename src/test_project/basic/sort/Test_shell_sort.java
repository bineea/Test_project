package test_project.basic.sort;

/**
 * 希尔排序：
 * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * 
 * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
 * 按增量序列个数k，对序列进行k 趟排序；
 * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 * 
 * 在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2...1}，称为增量序列。
 * 希尔排序的增量序列的选择与证明是个数学难题，我们选择的这个增量序列是比较常用的，也是希尔建议的增量，称为希尔增量，但其实这个增量序列不是最优的。
 * 
 * eg：数组{8,9,1,7,2,3,5,4,6,0}
 * 初始增量gap=length/2=5，意味着整个数组被分为5组，{8,3}，{9,5}，{1,4}，{7,6}，{2,0}；对这5组分别进行插入排序；
 *然后缩小增量gap=5/2=2，数组被分为2组，{3,1,0,9,7}，{5,6,8,4,2}；再对这2组分别进行插入排序；
 *最后再缩小增量gap=2/2=1，此时数据为1组，{0,2,1,4,3,5,7,6,9,8}；再进行插入排序即可结束 
 * 
 * @author bineea
 *
 */
public class Test_shell_sort {

	public static void main(String[] args) {
		Test_shell_sort test = new Test_shell_sort();
		test.shellSort(new int[] {8,9,1,7,2,3,5,4,6,0});
	}
	
	public void shellSort(int[] num) {
		if(num == null || num.length < 2) {
			System.out.println("待排序数组无法排序，数组为空或者数组长度小于2");
			return;
		}
		
		int gap = num.length / 2;
		while(gap >= 1) {
			for(int i=0; i<num.length; i+=gap) {
				this.insertSort(num, i, gap);
			}
			gap = gap / 2;
		}
		
		for(int i=0; i<num.length; i++) {
			System.out.print(num[i] + ";");
		}
	}
	
	public void insertSort(int[] num, int start, int gap) {
		
		for(int x=start+gap; x<num.length; x+=gap) {
			int insertIndex = x;
			for(int y=x-gap; y>=0; y-=gap) {
				if(num[y] > num[insertIndex]) {
					int temp = num[insertIndex];
					num[insertIndex] = num[y];
					num[y] = temp;
					insertIndex = y;
				}
			}
			
		}
	}
}
