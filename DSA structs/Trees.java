package com.company;
import java.util.*;

public class Tree {
    public static TreeNode<Integer> printTree(TreeNode<Integer> root){
        if(root == null){
            return null;
        }
        System.out.print(root.data + ": ");
        if(root.left != null){
            System.out.print("L" + root.left.data + " ");
        }
        if(root.right != null){
            System.out.print("R" + root.right.data);
        }
        System.out.println();
        printTree(root.left);
        printTree(root.right);
        return root;
    }

    public static TreeNode<Integer> treeInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the root data: ");
//        Alternate Code
//        int x = input.nextInt();
//        if(x != -1){
//            TreeNode<Integer> root = new TreeNode<Integer>(x);
//            root.left = treeInput();
//            root.right = treeInput();
//            return root;
//        }
//        return null;
        int data = input.nextInt();
        if(data == -1){
            return null;
        }
        TreeNode<Integer> root = new TreeNode<Integer>(data);
        TreeNode<Integer> leftChild = treeInput();
        TreeNode<Integer> rightChild = treeInput();
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }

    public static TreeNode<Integer> treeInputBetter(boolean isRoot, boolean isLeftChild, int parentData){
        if(isRoot == true){
            System.out.println("Enter the root data: ");
        }
        else{
            if(isLeftChild == true){
                System.out.println("Enter the left child of " + parentData + ": ");
            }
            else {
                System.out.println("Enter the right child of " + parentData + ": ");
            }
        }
        Scanner input = new Scanner(System.in);
        int data = input.nextInt();
        if(data == -1){
            return null;
        }
        TreeNode<Integer> root = new TreeNode<Integer>(data);
        TreeNode<Integer> leftChild = treeInputBetter(false, true, root.data);
        TreeNode<Integer> rightChild = treeInputBetter(false, false, root.data);
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }

    public static int numberOfNodes(TreeNode<Integer> root){
        if(root == null){
            return 0;
        }
        return numberOfNodes(root.left) + numberOfNodes(root.right) + 1;
    }

    public static int treeHeight(TreeNode<Integer> root) {
        if(root == null){
            return 0;
        }
        int leftHeight = treeHeight(root.left);
        int rightHeight = treeHeight(root.right);
        int subAns = Math.max(leftHeight, rightHeight);
        return subAns + 1;
    }

    public static BalancedBetterResult isBalancedBetter(TreeNode<Integer> root) {
        //Base case to check if the node is a null node
        if(root == null) {
            BalancedBetterResult ans = new BalancedBetterResult(0, true);
            return ans;
        }
        boolean balancedAnsThisNode = true; //the isBalanced part of this node answer that we plan to return to the next node
        BalancedBetterResult subAns1 = isBalancedBetter(root.left); //recursive call on the left side of the root
        BalancedBetterResult subAns2 = isBalancedBetter(root.right); //recursive call on the right side of the root
        //Checking if left or right returns false
        if(subAns1.isBalanced == false || subAns2.isBalanced == false){
            balancedAnsThisNode = false;
        }
        //for this node, checking if it is balanced itself from heights returned by left and right sides
        if(Math.abs(subAns1.height - subAns2.height) > 1) {
            balancedAnsThisNode = false;
        }
        //setting object values to return to the top layer
        BalancedBetterResult ans = new BalancedBetterResult(Math.max(subAns1.height, subAns2.height) + 1, balancedAnsThisNode);
        return ans;
    }

    public static TreeNode<Integer> levelWiseInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the root data: ");
        int rootData = input.nextInt();
        if(rootData == -1) {
            return null;
        }
        TreeNode<Integer> rootNode = new TreeNode<Integer>(rootData);
        Queue<TreeNode<Integer>> pendingNodes = new LinkedList<TreeNode<Integer>>();
        pendingNodes.add(rootNode);
        while(pendingNodes.isEmpty() == false) {
            System.out.println("Enter the left node of " + pendingNodes.peek().data);
            int leftNodeData = input.nextInt();
            if (leftNodeData != -1) {
                TreeNode<Integer> leftNode = new TreeNode<Integer>(leftNodeData);
                pendingNodes.peek().left = leftNode;
                pendingNodes.add(leftNode);
            }
            System.out.println("Enter the right node of " + pendingNodes.peek().data);
            int rightNodeData = input.nextInt();
            if (rightNodeData != -1) {
                TreeNode<Integer> rightNode = new TreeNode<Integer>(rightNodeData);
                pendingNodes.peek().right = rightNode;
                pendingNodes.add(rightNode);
            }
            pendingNodes.remove();
        }
        return rootNode;
    }

    public static boolean isBinaryTree(TreeNode<Integer> root) {
        //In this code we are doing induction step work before we call recursion on either left or right side
        //In this way we call bst function on each node only one time so the time complexity is the O(n)
        if(root == null) {
            return false;
        }
        if(root.left == null && root.right != null) {
            if(root.data <= root.right.data) {
                return true;
            }
            else{
                return false;
            }
        }
        else if(root.right == null && root.left != null) {
            if(root.data >= root.left.data) {
                return true;
            }
            else{
                return false;
            }
        }
        else if(root.left == null && root.right == null) {
            return true;
        }
        else {
            if(root.left.data <= root.data && root.data <= root.right.data) {
                boolean subResLeft = isBinaryTree(root.left);
                boolean subResRight = isBinaryTree(root.right);
                boolean subRes = subResLeft && subResRight;
                return subRes;
            }
            else{
                return false;
            }
        }
    }

    public static ArrayList<Integer> pathOfNode(TreeNode<Integer> root, int x) {
        if(root == null) {
            return null;
        }
        if(root.data == x) {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(root.data);
            return al;
        }
        ArrayList<Integer> leftAns = pathOfNode(root.left, x);
        ArrayList<Integer> rightAns =  pathOfNode(root.right, x);

        if(leftAns != null && rightAns == null) {
            leftAns.add(0, root.data);
            return leftAns;
        }

        else if(leftAns == null && rightAns != null){
            rightAns.add(0, root.data);
            return rightAns;
        }
        else {
            return null;
        }
    }

    public static int diameterOfTree (TreeNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        int leftDia = diameterOfTree(root.left);
        int rightDia = diameterOfTree(root.right);
        int potDia = treeHeight(root.left) + treeHeight(root.right) + 1;
        return Math.max(potDia, Math.max(leftDia, rightDia));
    }

    public static void main(String args[]){
        
    }
}

class BalancedBetterResult {
    int height;
    boolean isBalanced;
    BalancedBetterResult(int height, boolean isBalanced) {
        this.height = height;
        this.isBalanced = isBalanced;
    }
    BalancedBetterResult() {
    }
}

class DiaResult {
    int dia;
    TreeNode<Integer> x;
}

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode (T data){
        this.data = data;
        left = null;
        right = null;
    }
    TreeNode() {
    }
}

