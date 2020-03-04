package test_project.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *    值 0 代表空单元格；
 *     值 1 代表新鲜橘子；
 *     值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * 
 * 解题思路：
 * 所谓的广度优先搜索BFS算法
 * 
 * @author bineea
 *
 */
public class Test_13_array_short_time_bfs {

	public int orangesRotting(int[][] grid) {
		int count = 0;
		List<AlreadyRotten> rottenList = new ArrayList<AlreadyRotten>();
		Map<String, Integer> oranges = new HashMap<String, Integer>();
		for(int x=0; x<grid.length; x++) {
			for(int y=0; y<grid[x].length; y++) {
				if(grid[x][y] == 2)
					rottenList.add(new AlreadyRotten(x, y));
				else if(grid[x][y] == 1)
					oranges.put(String.format("%04d", x) + String.format("%04d", y), grid[x][y]);
			}
		}
		
		count = handleSingleLevel(rottenList, oranges, grid, count);
		
		if(oranges.isEmpty()) 
			return count;
		return -1;
    }
	
	
	public int handleSingleLevel(List<AlreadyRotten> rottenList, Map<String, Integer> oranges, int[][] grid, int count) {
		List<AlreadyRotten> result = new ArrayList<AlreadyRotten>();
		for(AlreadyRotten rotten : rottenList) {
			int x = rotten.x;
			int y = rotten.y;
			if(x-1 >= 0 && grid[x-1][y] == 1) {
				grid[x-1][y] = 2;
				result.add(new AlreadyRotten(x-1, y));
				oranges.remove(String.format("%04d", x-1) + String.format("%04d", y));
			}
			if(x+1 < grid.length && grid[x+1][y] == 1) {
				grid[x+1][y] = 2;
				result.add(new AlreadyRotten(x+1, y));
				oranges.remove(String.format("%04d", x+1) + String.format("%04d", y));
			}
			if(y-1 >= 0 && grid[x][y-1] == 1) {
				grid[x][y-1] = 2;
				result.add(new AlreadyRotten(x, y-1));
				oranges.remove(String.format("%04d", x) + String.format("%04d", y-1));
			}
			if(y+1 < grid[x].length && grid[x][y+1] == 1) {
				grid[x][y+1] = 2;
				result.add(new AlreadyRotten(x, y+1));
				oranges.remove(String.format("%04d", x) + String.format("%04d", y+1));
			}
		}
		if(result != null && !result.isEmpty()) {
			count += 1;
			count = handleSingleLevel(result, oranges, grid, count);
		}
		return count;
	}
	
	
	class AlreadyRotten {
		private int x;
		private int y;
		
		public AlreadyRotten (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	public static void main(String[] args) {
		Test_13_array_short_time_bfs test = new Test_13_array_short_time_bfs();
		int[][] grid0 = {{2,1,1},{1,1,0},{0,1,1}};
		System.out.println(test.orangesRotting(grid0));
		int[][] grid1 = {{2,1,1},{0,1,1},{1,0,1}};
		System.out.println(test.orangesRotting(grid1));
	}
}
