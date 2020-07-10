public class Solution {

	public static void leafPath(BinaryTreeNode<Integer> root, int k, String s) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            if(root.data == k) {
                System.out.println(s.substring(1) + " " + root.data);
            }
            return;
        }
        if(root.data >= k) {
            return;
        }
        leafPath(root.left, k - root.data, s + " " + root.data);
        leafPath(root.right, k - root.data, s + " " + root.data);
        return;
    }
	public static void rootToLeafPathsSumToK(BinaryTreeNode<Integer> root, int k) {
		// Write your code here
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            if(root.data == k) {
                System.out.println(root.data);
            }
            return;
        }
        leafPath(root, k, "");		
	}
}
