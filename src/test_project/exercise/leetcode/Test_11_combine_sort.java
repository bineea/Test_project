package test_project.exercise.leetcode;

/**
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * 初始化 A 和 B 的元素数量分别为 m 和 n
 * 
  * 输入:
 *A = [1,2,3,0,0,0], m = 3
 *B = [2,5,6],       n = 3
  *输出: [1,2,2,3,5,6]
 * 
  * 解题思路：
  * 实质为数列排序！！！
  * 
  * 利用数组 A 与 B 已经被排序的性质，我们可以使用双指针方法。
  * 这一方法将两个数组看作队列，每次从两个数组尾部取出比较大的数字放到数组A的尾部
 *A={1,2,3,0,0,0};B={2,5,6}
  * 比较A[2]和B[2]，将B[2]放到A[5]；然后比较A[2]和B[1]，以此类推
 * 
 * @author bineea
 *
 */
public class Test_11_combine_sort {

	public void merge_BL(int[] A, int m, int[] B, int n) {
		if(m==0) {
			for(int i=0; i<A.length; i++ ) {
				A[i] = B[i];
			}
		} else {
			int z = 0;
			for(int i=0; i<n; i++) {
				A[m+i] = B[i];
				for(int x=m+i; x>0; x--) {
					if(A[x] < A[x-1]) {
						z = A[x];
						A[x] = A[x-1];
						A[x-1] = z;
					}
				}
			}
		}
    }
	
	public void merge_FINAL(int[] A, int m, int[] B, int n) {
		for(int i=A.length-1; i>=0; i--) {
			if(n == 0)
				break;
			if(m == 0) {
				A[i] = B[n-1];
				n -= 1;
				continue;
			}
			if(A[m-1] >= B[n-1]) {
				A[i] = A[m-1];
				m -= 1;
			} else {
				A[i] = B[n-1];
				n -= 1;
			}
		}
	}

	public static void main(String[] args) {
		Test_11_combine_sort test = new Test_11_combine_sort();
		int[] A = new int[] {7,0,0,0};
		int[] B = new int[] {4,5,6};
		test.merge_FINAL(A, 1, B, 3);
		for(int i=0; i<A.length; i++) {
			System.out.println(A[i]);
		}
	}
}
