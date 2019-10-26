package com.java.recursion;

import java.util.Arrays;

public class Recursion {

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		int leftMax = root.left == null ? 0 : maxDepth(root.left);
		int rightMax = root.right == null ? 0 : maxDepth(root.right);
		return 1 + Math.max(leftMax, rightMax);
	}

	public static double myPow(double x, int n) {
		if (n == 0)
			return 1;
		if (n < 0) {
			x = 1 / x;
			n = -n;
		}

		return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
	}

	public static int climbStairs(int n) {
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		return num_ways(n, memo);
	}

	public static int num_ways(int n, int[] memo) {
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;
		if (memo[n] >= 0)
			return memo[n];

		memo[n] = num_ways(n - 1, memo) + num_ways(n - 2, memo);
		return memo[n];
	}

	public static int fibonacci(int N) {
		if (N < 2)
			return N;
		int a = 1, b = 0, c = 0;
		for (int i = 2; i <= N; i++) {
			c = a + b;
			b = a;
			a = c;
		}

		return c;
	}

	public static ListNode reverseLinkedList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode newHead = reverseLinkedList(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}

	public static String reverseString(char[] s) {
		if (s.length < 2)
			return Arrays.toString(s);
		reverseHelper(s, 0, s.length - 1);

		return Arrays.toString(s);
	}

	private static void reverseHelper(char[] s, int start, int end) {
		if (start >= end)
			return;

		// swap
		char temp = s[start];
		s[start++] = s[end];
		s[end--] = temp;

		reverseHelper(s, start, end);

	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
