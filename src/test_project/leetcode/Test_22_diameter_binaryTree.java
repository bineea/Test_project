package test_project.leetcode;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Test_22_diameter_binaryTree {

	
	public int diameterOfBinaryTree(TreeNode root) {
		int res = 0;
		
		
		
		return res;
    }
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args) {
		Test_22_diameter_binaryTree test = new Test_22_diameter_binaryTree();
		System.out.println(test.diameterOfBinaryTree(null));
	}
}
