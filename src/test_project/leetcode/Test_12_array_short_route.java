package test_project.leetcode;

/**
 * 
 * @author binee
 *
 */

public class Test_12_array_short_route {

	public boolean exist(char[][] board, String word) {
		char[] wordCharArray = word.toCharArray();
		for(int x=0; x<board.length; x++) {
			for(int y=0; y<board[0].length; y++) {
				if(board[x][y] == wordCharArray[0]) {
					char[][] bak = new char[board.length][board[0].length];
					for(int a=0;a<board.length;a++) {
						System.arraycopy(board[a], 0, bak[a], 0, board[0].length);
					}
					if(findRoute(x, y, 0, bak, word))
						return true;
				}
			}
		}
		return false;
    }
	
	public boolean findRoute(int x, int y, int sortNo, char[][] board, String word) {
		if(sortNo == word.length() - 1)
			return true;
		String wordStr = word; 
		sortNo += 1;
		char[] wordStrCharArray = wordStr.substring(sortNo).toCharArray();
		boolean res1 = false, res2 = false, res3 = false, res4 = false;
		if(x-1>=0 && board[x-1][y] == wordStrCharArray[0]) {
			char[][] bak = new char[board.length][board[0].length];
			for(int a=0;a<board.length;a++) {
				System.arraycopy(board[a], 0, bak[a], 0, board[0].length);
			}
			bak[x][y] = '#';
			bak[x-1][y] = '#';
			res1 = findRoute(x-1, y, sortNo, bak, word);
		}
		if(x+1<board.length && board[x+1][y] == wordStrCharArray[0]) {
			char[][] bak = new char[board.length][board[0].length];
			for(int a=0;a<board.length;a++) {
				System.arraycopy(board[a], 0, bak[a], 0, board[0].length);
			}
			bak[x][y] = '#';
			bak[x+1][y] = '#';
			res2 = findRoute(x+1, y, sortNo, bak, word);
		}
		if(y-1>=0 && board[x][y-1] == wordStrCharArray[0]) {
			char[][] bak = new char[board.length][board[0].length];
			for(int a=0;a<board.length;a++) {
				System.arraycopy(board[a], 0, bak[a], 0, board[0].length);
			}
			bak[x][y] = '#';
			bak[x][y-1] = '#';
			res3 = findRoute(x, y-1, sortNo, bak, word);
		}
		if(y+1<board[0].length && board[x][y+1] == wordStrCharArray[0]) {
			char[][] bak = new char[board.length][board[0].length];
			for(int a=0;a<board.length;a++) {
				System.arraycopy(board[a], 0, bak[a], 0, board[0].length);
			}
			bak[x][y] = '#';
			bak[x][y+1] = '#';
			res4 = findRoute(x, y+1, sortNo, bak, word);
		}
		if(res1 || res2 || res3 || res4)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Test_12_array_short_route test = new Test_12_array_short_route();
		
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCCED";
		char[][] board1 = {{'A','B'},{'C','D'}};
		String word1 = "ABCD";
		char[][] board2 = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
		String word2 = "AAB";
		char[][] board3 = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
		String word3 = "ABCESEEEFS";

		System.out.println(test.exist(board3, word3));
	}
}
