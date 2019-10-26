package com.java.binarysearch;

import java.util.Arrays;

public class BinarySearch {
	
	public static int[] findClosestElements(int[] arr, int k, int x) {
		// Input: [1,2,3,4,5], k=4, x=3
		// Output: [1,2,3,4]
		int lo = 0, hi = arr.length - k - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // for subarray starting at mid with size k+1, we compare element of two ends to eliminate the loser
            if (Math.abs(x - arr[mid]) > Math.abs(x - arr[mid+k])) {
                lo = mid + 1; // arr[mid] is the one further away from x, eliminate range[lo, mid]
            } else {
                hi = mid - 1; // arr[mid+k] is the one further away, all [mid, hi] will have simiar situation, elimiate them
            }                
        } 
        return Arrays.copyOfRange(arr, lo, lo+k);
    }
	
	public static int[] searchRange(int[] nums, int target) {
		//Input: nums = [5,7,7,8,8,10], target = 8
		//Output: [3,4]
        int start = startPosition(nums,target);
        
        if(start == nums.length || nums[start]!=target)
            return new int[]{-1,-1};
        
        return new int[]{start,startPosition(nums,target+1)-1};
    }
    
    public static int startPosition(int[] nums, int target){
        int l = 0, r=nums.length-1,mid;
        
        if(nums.length == 0)
            return 0;
        
        if(target>nums[r])
            return nums.length;
        
        while(l<r){
            mid = l+(r-l)/2;
            if(nums[mid]<target){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        
        return l;
    }
	
	public static int RotatedArraySearch(int[] nums, int target) {
		// LEETCODE PROBLEM: sorted array rotated at some unknown pivot
		// e.g. [4,5,6,7,0,1,2]
        int l=0,r=nums.length-1,mid;
        while(l<r){
            mid = l+(r-l)/2;
            if(nums[mid]>nums[r]){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        int index = l;
        l =0; r= nums.length-1; 
        int realMid;
        while(l<=r){
            mid = l + (r-l)/2;
            realMid = (index+mid)%nums.length;
            if(nums[realMid] == target)
                return realMid;
            else if(nums[realMid]>target){
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return -1;
    }
	
	public static int findMin(int[] nums) {
		// LEETCODE PROBLEM: find min of a sorted rotated array
        int l = 0, r=nums.length-1, mid;
        while(l<r){
            mid = l + (r-l)/2;
            if(nums[mid]>nums[r]){
                l = mid+1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }
	
	public static int sqrt(int x) {
		//LEETCODE PROBLEM: find sqrt of given num x using binary search
		if(x<=0)
            return 0;
        int l =1, r= x/2,mid;
        
        while(true){
        	// to prevent overflow
            mid = l + (r-l)/2;
            // mid*mid > x
            if (mid > x/mid){
                r = mid -1;
            } else {
                if((mid+1) > x/(mid+1))
                    return mid;
                l = mid + 1;
            }
        }
	}

}
