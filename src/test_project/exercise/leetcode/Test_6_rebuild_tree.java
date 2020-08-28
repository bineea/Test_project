package test_project.exercise.leetcode;

/**
  * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
  * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
  * 
  * 前序遍历：（1）访问根结点（2）前序遍历左子树（3）前序遍历右子树
  * 中序遍历：（1）中序遍历左子树（2）访问根结点（3）中序遍历右子树
  * 后序遍历：（1）后序遍历左子树（2）后序遍历右子树（3）访问根结点
 * @author binee
 *
 */
public class Test_6_rebuild_tree {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length)
			return null;
		int nodeCount = preorder.length;
		TreeNode root = new TreeNode(preorder[0]);
		
		for(int i=0; i<nodeCount; i++) {
			if(inorder[i] == preorder[0]) {
				
				int[] left_childpreorder = new int[i];
				int[] right_childpredorder = new int[nodeCount-i-1];
				int[] left_childinorder = new int[i];
				int[] right_childindorder = new int[nodeCount-i-1];
				
				for(int x=0; x<i; x++) {
					left_childpreorder[x] = preorder[x+1];
					left_childinorder[x] = inorder[x];
				}
				
				for(int x=0; x<nodeCount-i-1; x++) {
					right_childpredorder[x] = preorder[i+1+x];
					right_childindorder[x] = inorder[i+1+x];
				}
				root.left = buildTree(left_childpreorder, left_childinorder);
				root.right = buildTree(right_childpredorder, right_childindorder);
				break;
			}
		}
		
		return root;
    }
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args) {
		
	}
}
