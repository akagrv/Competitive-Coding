package com.java.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode head = createBST(nums, 0, nums.length - 1);
        return head;
    }

    public TreeNode createBST(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = createBST(num, low, mid - 1);
        node.right = createBST(num, mid + 1, high);
        return node;
    }
	
	public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    
    public boolean isValidBST(TreeNode root, Integer lowerLimit, Integer upperLimit){
        if(root == null)
            return true;
        if(upperLimit != null && root.val>=upperLimit)   return false;
        if(lowerLimit != null && root.val<=lowerLimit)   return false;
        if(!isValidBST(root.left, lowerLimit, root.val))  return false;
        if(!isValidBST(root.right,root.val,upperLimit))   return false;;
        
        return true;
    }
	
	public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    
    public boolean isMirror(TreeNode leftRoot, TreeNode rightRoot){
        if(leftRoot == null && rightRoot == null)   return true;
        if(leftRoot == null || rightRoot == null)   return false;
        return (leftRoot.val == rightRoot.val) && isMirror(leftRoot.left,rightRoot.right) && isMirror(leftRoot.right,rightRoot.left);
    }
	
	public List<List<Integer>> levelOrderUsingQueue(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null)
            return res;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            List<Integer> list = new ArrayList<Integer>();
            int level = q.size();
            for(int i=0;i<level;i++){
                if(q.peek().left != null)   q.offer(q.peek().left);
                if(q.peek().right != null)  q.offer(q.peek().right);
                list.add(q.poll().val);
            }
            res.add(list);
        }
        
        return res;
    }
	
	public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null)
            return res;
        int height = getHeightOfTree(root);
        System.out.println(height);
        for(int h=1;h<=height;h++){
            List<Integer> nodes = new ArrayList<Integer>();
            res.add(getLevelNodes(root,h, nodes));
        }
        
        return res;
    }
    
    public List<Integer> getLevelNodes(TreeNode root, int level, List<Integer> nodes){
        if(level == 1){
            nodes.add(root.val);
        }
        else {
            if(root.left != null)
                getLevelNodes(root.left,level-1, nodes);
            if(root.right != null)
                getLevelNodes(root.right,level-1, nodes);
        }
        return nodes;
    }
    
    public int getHeightOfTree(TreeNode root){
        if(root == null)
            return 0;
        return Math.max(getHeightOfTree(root.left),getHeightOfTree(root.right))+1;
    }
	
	public static List<Integer> inorderTraversalIterative(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        
        List<Integer> res = new ArrayList<Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        while(true) {
            if (root!=null) {
                stack.push(root);
                root = root.left;
            } else {
                if(stack.empty())
                    break;
                //it is a leaf node
                TreeNode top = stack.pop();
                res.add(top.val);
                root = top.right;
            }
        }
        
        return res;
    }

	public static List<Integer> preorderTraversalIterative(TreeNode root) {
        if(root == null)
            return new ArrayList<Integer>();
        
        List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
        
        stack.push(root);
        while(!stack.isEmpty()){
            //visit the top node and push right and left into the stack
            TreeNode top = stack.pop();
            
            list.add(top.val);
            if(top.right != null)   stack.push(top.right);
            if(top.left != null)   stack.push(top.left);
        }
		return list;
    }

	public static List<Integer> preorderTraversalRecursive(TreeNode root) {
		// Root->Left->Right
		List<Integer> res = new ArrayList<Integer>();
		preorderTraversal(root,res);
		return res;
	}
	
	private static void preorderTraversal(TreeNode root, List<Integer> res) {
		// TODO Auto-generated method stub
		if(root == null)
			return;
		
		res.add(root.val);
		preorderTraversal(root.left, res);
		preorderTraversal(root.right, res);
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
