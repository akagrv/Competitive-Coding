package com.java.dp;

import java.util.Arrays;

public class DynamicProgramming {

	public int longestCommonSubsequenceBottomUp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); ++i)
            for (int j = 0; j < s2.length(); ++j)
                if (s1.charAt(i) == s2.charAt(j)) dp[i + 1][j + 1] = 1 + dp[i][j];
                else dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);
        return dp[s1.length()][s2.length()];
    }
	
	public static int longestCommonSubsequence(String s1, String s2) {
		if(s1.length()==0 || s2.length()==0)	return 0;
		int[][] LCS = new int[s1.length()+1][s2.length()+1];
		return LCSHelper(s1,s1.length()-1,s2,s2.length()-1,LCS);
	}
	
	public static int LCSHelper(String s1, int l1, String s2, int l2, int[][] LCS) {
		if(l1<0 || l2<0)
			return 0;
		if(LCS[l1][l2] != 0)	return LCS[l1][l2];
		
		if(s1.charAt(l1) == s2.charAt(l2)) { 
			return LCS[l1][l2] = LCSHelper(s1,l1-1,s2,l2-1, LCS) + 1;
		}else {
			return LCS[l1][l2] = Math.max(LCSHelper(s1,l1-1,s2,l2, LCS),LCSHelper(s1,l1,s2,l2-1, LCS));
		}
	}
	
	public static int longestSubstring(String str) {
		if (str.length() < 2)
			return str.length();
		int[] ls = new int[str.length()];

		Arrays.fill(ls, 1);
		int max =1;

		for (int i = 1; i < str.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (str.charAt(i) != str.charAt(j) && ls[i] < ls[j] + 1) {
					ls[i] = ls[j] + 1;
					max = Math.max(max, ls[i]);
				}else {
					ls[i] = 1;
					break;
				}
			}
		}
		return max;
	}

	public static int longestIncreasingSubsequence(int[] arr) {
		if (arr.length < 2)
			return arr.length;
		int[] LIS = new int[arr.length];

		Arrays.fill(LIS, 1);
		int max =1;

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
					max = Math.max(max, LIS[i]);
				}
			}
		}
		return max;
	}

	public static int maxSubArraySum(int[] nums) {
		// KADANE's Algorithm
		if (nums.length < 1)
			return 0;
		int maxSoFar = nums[0], maxEndingHere = nums[0];
		for (int i = 1; i < nums.length; i++) {
			maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

}
