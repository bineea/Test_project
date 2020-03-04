package test_project.leetcode;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * 
 * 解题思路：
 * 深度优先搜索DFS算法
 * @author binee
 *
 */
public class Test_14_array_arrive_dfs {
	
	public int movingCount(int m, int n, int k) {
		
		int[][] grid = new int[m][n];
		int count = singleMove(0, 0, grid, k);;
		return count;
    }
	
	public int singleMove(int x, int y, int[][] grid, int k) {
		int count = 0, sum = 0;
		if( x < 0 || y< 0 || x >= grid.length || y >= grid[x].length) return 0;
		String sumStr = String.valueOf(x).concat(String.valueOf(y));
		for(int i=0; i<sumStr.length(); i++) {
			sum += sumStr.charAt(i) - '0';
		}
		if(sum <= k && grid[x][y] == 0) {
			grid[x][y] = 1;
			count += 1;
			count += singleMove(x-1, y, grid, k);
			count += singleMove(x+1, y, grid, k);
			count += singleMove(x, y-1, grid, k);
			count += singleMove(x, y+1, grid, k);
		}
		return count;
	}
	
	public static void main(String[] args) {
		Test_14_array_arrive_dfs test = new Test_14_array_arrive_dfs();
		System.out.println(test.movingCount(3,1,0));
	}
}
