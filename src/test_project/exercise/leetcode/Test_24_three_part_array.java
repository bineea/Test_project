package test_project.exercise.leetcode;

/**
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。 形式上，如果可以找出索引 i+1 < j 且满足
 * (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1]
 * + ... + A[A.length - 1]) 就可以将数组三等分。
 * 
 * @author bineea
 *
 */
public class Test_24_three_part_array {

	public boolean canThreePartsEqualSum(int[] A) {

		if (A.length < 3)
			return false;
		int first = 0, all = 0;
		for (int z = 0; z < A.length; z++) {
			all += A[z];
		}
		for (int i = 0; i < A.length - 2; i++) {
			int second = 0;
			first += A[i];
			if (first * 3 != all) {
				continue;
			}
			for (int x = i + 1; x < A.length - 1; x++) {
				second += A[x];
				if (first == second && second == all - first - second)
					return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Test_24_three_part_array test = new Test_24_three_part_array();
		System.out.println(test.canThreePartsEqualSum(new int[] { 0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1 }));
		System.out.println(test.canThreePartsEqualSum(new int[] { 0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1 }));
		System.out.println(test.canThreePartsEqualSum(new int[] { 3, 3, 6, 5, -2, 2, 5, 1, -9, 4 }));
	}
}
