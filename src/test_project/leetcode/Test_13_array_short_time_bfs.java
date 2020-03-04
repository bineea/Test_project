package test_project.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ڸ����������У�ÿ����Ԫ���������������ֵ֮һ��
 *    ֵ 0 ����յ�Ԫ��
 *     ֵ 1 �����������ӣ�
 *     ֵ 2 �����õ����ӡ�
 * ÿ���ӣ��κ��븯�õ����ӣ��� 4 ���������ϣ����ڵ��������Ӷ��ḯ�á�
 * ����ֱ����Ԫ����û����������Ϊֹ�����뾭������С����������������ܣ����� -1��
 * 
 * ����˼·��
 * ��ν�Ĺ����������BFS�㷨
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
