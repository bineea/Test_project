package test_project.exercise.leetcode;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * 
 * 
 * 
 * @author binee
 *
 */
public class Test_26_max_Island_dfs {
	
	public int maxAreaOfIsland(int[][] grid) {
		int res = 0;
		
		for(int x=0; x<grid.length; x++) {
			 
			for(int y=0; y<grid[x].length; y++) {
				if(grid[x][y] == 1) {
					res = Math.max(res, handleMaxIsIand(grid, x, y));
				}
			}
		}
		
		return res;
    }
	
	public int handleMaxIsIand(int[][] grid, int x, int y) {
		int res = 0;
		
		if(x < 0 || x >=grid.length || y<0 || y >=grid[x].length || grid[x][y] != 1) {
			return res;
		}
		grid[x][y] = 0;
		res += 1;
		res += handleMaxIsIand(grid, x-1, y);
		res += handleMaxIsIand(grid, x+1, y);
		res += handleMaxIsIand(grid, x, y-1);
		res += handleMaxIsIand(grid, x, y+1);
		return res;
	}

	
	public static void main(String[] args) {
		Test_26_max_Island_dfs test = new Test_26_max_Island_dfs();
		int[][] array0 = new int[][] {
			{0,0,1,0,0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,1,1,0,0,0},
			{0,1,1,0,1,0,0,0,0,0,0,0,0},
			{0,1,0,0,1,1,0,0,1,0,1,0,0},
			{0,1,0,0,1,1,0,0,1,1,1,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,1,1,0,0,0,0}
		};//res == 6
		System.out.println(test.maxAreaOfIsland(array0));
	}
}
