package test_project.exercise.leetcode;

/**
  * ����ĳ��������ǰ���������������Ľ�������ؽ��ö�������
  * ���������ǰ���������������Ľ���ж������ظ������֡�
  * 
  * ǰ���������1�����ʸ���㣨2��ǰ�������������3��ǰ�����������
  * �����������1�����������������2�����ʸ���㣨3���������������
  * �����������1�����������������2�����������������3�����ʸ����
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
