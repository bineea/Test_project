package test_project.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * 
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * 
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * 
 * 所谓的深度优先搜索DFS算法，就是可以理解为暴力法遍历矩阵中所有字符串可能性。DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
 * 
 * @author binee
 *
 */

public class Test_12_array_short_route {

	public boolean exist_first(char[][] board, String word) {
		if(word.length() > (board.length * board[0].length))
			return false;
		char[] wordCharArray = word.toCharArray();
		Map<String, Character> routeData = new HashMap<String, Character>();
		for(int x=0; x<board.length; x++) {
			for(int y=0; y<board[0].length; y++) {
				if(board[x][y] == wordCharArray[0]) {
					routeData.put(String.format("%03d", x) + String.format("%03d", y), board[x][y]);
					if(findRoute_first(x, y, 0, routeData, board, word))
						return true;
					else
						routeData.clear();
				}
			}
		}
		return false;
    }
	
	public boolean findRoute_first(int x, int y, int sortNo, Map<String, Character> routeData, char[][] board, String word) {
		if(sortNo == word.length() - 1)
			return true;
		String wordStr = word; 
		sortNo += 1;
		char[] wordStrCharArray = wordStr.substring(sortNo).toCharArray();
		boolean res1 = false, res2 = false, res3 = false, res4 = false;
		if(x-1>=0 && board[x-1][y] == wordStrCharArray[0] && routeData.get(String.format("%03d", x-1) + String.format("%03d", y)) == null) {
			routeData.put(String.format("%03d", x-1) + String.format("%03d", y), board[x-1][y]);
			res1 = findRoute_first(x-1, y, sortNo, routeData, board, word);
			if(!res1) {
				routeData.remove(String.format("%03d", x-1) + String.format("%03d", y));
			} else
				return res1;
		}
		if(x+1<board.length && board[x+1][y] == wordStrCharArray[0] && routeData.get(String.format("%03d", x+1) + String.format("%03d", y)) == null) {
			routeData.put(String.format("%03d", x+1) + String.format("%03d", y), board[x+1][y]);
			res2 = findRoute_first(x+1, y, sortNo, routeData, board, word);
			if(!res2) {
				routeData.remove(String.format("%03d", x+1) + String.format("%03d", y));
			} else
				return res2;
		}
		if(y-1>=0 && board[x][y-1] == wordStrCharArray[0] && routeData.get(String.format("%03d", x) + String.format("%03d", y-1)) == null) {
			routeData.put(String.format("%03d", x) + String.format("%03d", y-1), board[x][y-1]);
			res3 = findRoute_first(x, y-1, sortNo, routeData, board, word);
			if(!res3) {
				routeData.remove(String.format("%03d", x) + String.format("%03d", y-1));
			} else
				return res3;
		}
		if(y+1<board[0].length && board[x][y+1] == wordStrCharArray[0] && routeData.get(String.format("%03d", x) + String.format("%03d", y+1)) == null) {
			routeData.put(String.format("%03d", x) + String.format("%03d", y+1), board[x][y+1]);
			res4 = findRoute_first(x, y+1, sortNo, routeData, board, word);
			if(!res4) {
				routeData.remove(String.format("%03d", x) + String.format("%03d", y+1));
			} else
				return res4;
		}
		return false;
	}
	
	public boolean exist_FINAL(char[][] board, String word) {
		if(word.length() > (board.length * board[0].length))
			return false;
		char[] wordCharArray = word.toCharArray();
		for(int x=0; x<board.length; x++) {
			for(int y=0; y<board[0].length; y++) {
				if(board[x][y] == wordCharArray[0]) {
					if(findRoute_FINAL(x, y, 0, board, wordCharArray))
						return true;
				}
			}
		}
		return false;
    }
	
	public boolean findRoute_FINAL(int x, int y, int sortNo, char[][] board, char[] wordCharArray) {
		if(sortNo >= wordCharArray.length)
			return false;
		if(x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y] != wordCharArray[sortNo])
			return false;
		if(sortNo == wordCharArray.length - 1)
			return true;
		sortNo += 1;
		char temp = board[x][y];
		board[x][y] = '#';
		boolean res = findRoute_FINAL(x-1, y, sortNo, board, wordCharArray) || findRoute_FINAL(x+1, y, sortNo, board, wordCharArray)
				|| findRoute_FINAL(x, y-1, sortNo, board, wordCharArray) || findRoute_FINAL(x, y+1, sortNo, board, wordCharArray);
		if(!res)
			board[x][y] = temp;
		return res;
	}
	
	public static void main(String[] args) {
		Test_12_array_short_route test = new Test_12_array_short_route();
		
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCCED";
		System.out.println(test.exist_FINAL(board, word));
		char[][] board1 = {{'A','B'},{'C','D'}};
		String word1 = "ABCD";
		System.out.println(test.exist_FINAL(board1, word1));
		char[][] board2 = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
		String word2 = "AAB";
		System.out.println(test.exist_FINAL(board2, word2));
		char[][] board3 = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
		String word3 = "ABCESEEEFS";
		System.out.println(test.exist_FINAL(board3, word3));
		char[][] board4 = {{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','b'}};
		String word4 = "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		System.out.println(test.exist_FINAL(board4, word4));
	}
}
