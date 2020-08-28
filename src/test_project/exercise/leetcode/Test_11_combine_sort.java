package test_project.exercise.leetcode;

/**
 * ������������������ A �� B������ A ��ĩ�����㹻�Ļ���ռ����� B�� ��дһ���������� B �ϲ��� A ������
 * ��ʼ�� A �� B ��Ԫ�������ֱ�Ϊ m �� n
 * 
  * ����:
 *A = [1,2,3,0,0,0], m = 3
 *B = [2,5,6],       n = 3
  *���: [1,2,2,3,5,6]
 * 
  * ����˼·��
  * ʵ��Ϊ�������򣡣���
  * 
  * �������� A �� B �Ѿ�����������ʣ����ǿ���ʹ��˫ָ�뷽����
  * ��һ�������������鿴�����У�ÿ�δ���������β��ȡ���Ƚϴ�����ַŵ�����A��β��
 *A={1,2,3,0,0,0};B={2,5,6}
  * �Ƚ�A[2]��B[2]����B[2]�ŵ�A[5]��Ȼ��Ƚ�A[2]��B[1]���Դ�����
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
