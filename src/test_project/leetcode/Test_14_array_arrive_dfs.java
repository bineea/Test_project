package test_project.leetcode;

/**
 * ������һ��m��n�еķ��񣬴����� [0,0] ������ [m-1,n-1] ��һ�������˴����� [0, 0] �ĸ��ӿ�ʼ�ƶ�����ÿ�ο��������ҡ��ϡ����ƶ�һ�񣨲����ƶ��������⣩��Ҳ���ܽ�������������������λ֮�ʹ���k�ĸ��ӡ�
 * ���磬��kΪ18ʱ���������ܹ����뷽�� [35, 37] ����Ϊ3+5+3+7=18���������ܽ��뷽�� [35, 38]����Ϊ3+5+3+8=19�����ʸû������ܹ�������ٸ����ӣ�
 * 
 * ����˼·��
 * �����������DFS�㷨
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
