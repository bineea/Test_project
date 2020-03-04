package test_project.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * �����һ�������������ж���һ���������Ƿ����һ������ĳ�ַ��������ַ���·����·�����ԴӾ����е�����һ��ʼ��ÿһ�������ھ����������ҡ��ϡ����ƶ�һ�����һ��·�������˾����ĳһ����ô��·�������ٴν���ø��ӡ����磬�������3��4�ľ����а���һ���ַ�����bfce����·����·���е���ĸ�üӴֱ������
 * 
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * 
 * �������в������ַ�����abfb����·������Ϊ�ַ����ĵ�һ���ַ�bռ���˾����еĵ�һ�еڶ�������֮��·�������ٴν���������ӡ�
 * 
 * ��ν�������������DFS�㷨�����ǿ������Ϊ���������������������ַ��������ԡ�DFS ͨ���ݹ飬�ȳ�һ�������ѵ��ף��ٻ������ϸ��ڵ㣬����һ�������������Դ����ơ�
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
