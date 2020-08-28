package test_project.exercise.leetcode;

/**
  * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
  * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author binee
 */
public class Test_3_find_in_array {
	public boolean findNumberIn2DArray(int[][] matrix, int target) {
		if(matrix.length <= 0) return false;
		if(matrix[0].length <= 0) return false;
		int min = matrix[0][0];
		int max = matrix[matrix.length-1][matrix[0].length-1];
		if(target >= min && target <= max)
		{
			for(int row=0; row<matrix.length; row++) {
				if(target == matrix[row][0] || target == matrix[row][matrix[0].length-1])
					return true;
				if(target > matrix[row][0] && target < matrix[row][matrix[0].length-1]) {
					for(int col=0; col<matrix[0].length; col++) {
						if(target == matrix[row][col])
							return true;
					}
				}
			}
		}
		return false;
    }
	
	public static void main(String[] args) {
		int[][] matrix = {{-5}};
		Test_3_find_in_array test = new Test_3_find_in_array();
		boolean result = test.findNumberIn2DArray(matrix, -5);
		System.out.println(result);
	}
}
