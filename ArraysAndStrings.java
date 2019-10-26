package com.java.arraysandstrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class ArraysAndStrings {
	
	public int firstUniqChar(String s) {
		// LEETCODE: First Unique Character in a string
        Map<Character,Integer> map = new HashMap<>();
        
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        
        for(int i=0;i<s.length();i++){
            if(map.get(s.charAt(i)) == 1)
                return i;
        }
        
        return -1;
    }
	
	public void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1,k=m+n-1;
        while(j>=0){
            if(i<0 || nums1[i]<nums2[j]){
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }
    }
	
	public static boolean isMonotonic(int[] arr) {
		//FACEBOOK INTERVIEW PROBLEM: Check if arr seq is monotonic
		
		boolean isIncreasing = true, isDecreasing = true;
		for(int i=1;i<arr.length;i++) {
			if(arr[i-1] < arr[i]) {
				isDecreasing = false;
			} else if (arr[i-1] > arr[i]){
				isIncreasing = false;
			}
		}
		
		return isDecreasing || isIncreasing;
	}
	public int reverseNumber(int x) {
        int result = 0;

        while (x != 0)   // sufficient for handling both +ve and -ve
        {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)  //great way to handle overflow
            { return 0; }
            result = newResult;
            x = x / 10;
        }

        return result;
    }
	
	public static int  Balloon(String s) {
		int moves = 999999;
		if(s.length() < 7)
			return 0;
		/*
		 * int[] freq = new int[26]; for(int i=0;i<s.length();i++) {
		 * freq[s.charAt(i)-'A']++; } for(int i=0;i<freq.length;i++) { if) }
		 */
		
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		for(int i=0;i<s.length();i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
		}
		
		for(Entry<Character, Integer> entry:map.entrySet()) {
			char key = entry.getKey();
			if(key == 'A' || key == 'B' || key == 'N') {
				moves = Math.min(moves, entry.getValue());
			}
			if (key == 'L' ||key == 'O') {
				moves = Math.min(moves, entry.getValue()/2);
			}
		}
		return (moves == 999999) ? 0 : moves;
	}
	
	public List<List<Integer>> threeSum(int[] nums) {
		// a+b+c = 0
        Arrays.sort(nums);
        
        if((nums.length>0 && nums[0]>0) || nums.length<3)
            return new ArrayList<List<Integer>>();
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        for(int i=0;i<nums.length-2;i++){
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int start = i+1, end = nums.length-1, target=nums[i];
                while(start<end){
                    if(nums[start]+nums[end]+target == 0){
                        res.add(Arrays.asList(target,nums[start],nums[end]));
                        while (start < end && nums[start] == nums[start+1]) start++;
                        while (start < end && nums[end] == nums[end-1]) end--;
                        start++;
                        end--;
                    }
                    else if(nums[start]+nums[end]+target<0){
                        start++;
                    } else{
                        end--;
                    }     
                }
            }
        }
        return res;
    }
	
	public static String diverse(int A, int B, int C) {
		// PROBLEM: letters can be 'a','b' and 'c' only.
		// a string is diverse if three consecutive letter occurs i.e. aaa or bbb or ccc
		int[] counts = new int[4];
		counts['a'] = A;
		counts['b'] = B;
		counts['c'] = C;
		
		Arrays.sort(counts);
		
		System.out.println(Arrays.toString(counts));
		
		return "";
	}
	
	public static String Decompression(String compressed) {
		//compressed = 2[3[a]cd]a
		// op = aaacdaaacda
		
		char[] c = compressed.toCharArray();
		Stack<Integer> numStack = new Stack<Integer>();
		Stack<Character> charStack = new Stack<Character>();
		StringBuilder res = new StringBuilder();
		
		for(int i=0; i<c.length; i++) {
			if(c[i] == '[') {
				charStack.push(c[i]);
			}
			if(c[i] >= '0' && c[i] <= '9') {
				StringBuilder num = new StringBuilder();
				while(i<c.length && c[i] >= '0' && c[i] <= '9') {
					num.append(c[i++]);
				}
				i--;
				numStack.push(Integer.parseInt(num.toString()));
			}
			if(c[i] >= 'a' && c[i] <= 'z') {
				while(i<c.length && c[i] >= 'a' && c[i] <= 'z') {
					res.append(c[i++]);
				}
				i--;
			}
			if(c[i] == ']') {
				if(!charStack.isEmpty()) {
					charStack.pop();
					if(!numStack.isEmpty()) {
						String temp = res.toString();
						int reps = numStack.pop();
						for(int j=0;j<reps-1;j++) {
							res.append(temp);
						}
					}
				}
			}
		}
		
		return res.toString();
	}

	public static boolean makeBricks(int small, int big, int goal) {
		if(goal> big*5+small) return false;
		if(goal%5> small) return false;
		return true;
	}

	public static boolean evenlySpaced(int a, int b, int c) {
		if (a == b && a == c)
			return true;
		if (a == b || a == c || b == c)
			return false;
		int diff1, diff2, diff3;
		diff1 = Math.abs(a - b);
		diff2 = Math.abs(b - c);
		diff3 = Math.abs(a - c);

		if (diff1 == diff2)
			return true;
		if (diff2 == diff3)
			return true;
		if (diff1 == diff3)
			return true;
		return false;
	}

	public static String[] encoder(String[] raw, String[] code_words) {
		Map<String, String> encodeMap = new HashMap<String, String>();
		int index = 0;
		String[] res = new String[raw.length];
		for (int i = 0; i < raw.length; i++) {
			if (encodeMap.containsKey(raw[i])) {
				res[i] = encodeMap.get(raw[i]);
			} else {
				encodeMap.put(raw[i], code_words[index]);
				index++;
				res[i] = encodeMap.get(raw[i]);
			}
		}
		return res;
	}

	public static Map<String, Integer> wordCount(String[] strings) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			map.put(strings[i], map.getOrDefault(strings[i], 0) + 1);
		}
		return map;
	}

	public static Map<String, String> pairs(String[] strings) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < strings.length; i++) {
			map.put(Character.toString(strings[i].charAt(0)),
					Character.toString(strings[i].charAt(strings[i].length() - 1)));
		}
		return map;
	}

	public static Map<String, Integer> wordLen(String[] strings) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			map.putIfAbsent(strings[i], strings[i].length());
		}
		return map;
	}

	public static int interpret(int value, String[] commands, int[] args) {
		// value = 1;
		// commands = new String[] {"+", "*"};
		// args = new int[] {1,3};

		int result = value;
		for (int i = 0; i < commands.length; i++) {
			if (commands[i] == "+") {
				result = result + args[i];
			} else if (commands[i] == "-") {
				result = result - args[i];
			} else if (commands[i] == "*") {
				result = result * args[i];
			} else {
				return -1;
			}
		}
		return result;
	}

	public static int[] sort(int[] a) {
		if (a.length <= 1)
			return a;
		int index = 0;
		a[index] = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] == a[index]) {
				continue;
			} else {
				index++;
				a[index] = a[i];
			}
		}
		return Arrays.copyOf(a, index + 1);
	}

	public static Map<String, String> mapShare(Map<String, String> map) {
		if (map.containsKey("a")) {
			map.put("b", map.get("a"));
		}

		if (map.containsKey("c"))
			map.remove("c");

		return map;
	}

	public static boolean canBalance(int[] nums) {
		if (nums.length <= 1)
			return false;
		int i = 0, j = nums.length - 1;
		int sumL = 0, sumR = 0;
		while (i <= j) {
			if (sumL >= sumR) {
				sumR += nums[j];
				j--;
			} else {
				sumL += nums[i];
				i++;
			}
		}
		return (sumL == sumR) ? true : false;
	}

	public static int sumNumbers(String str) {
		// PROBLEM: Given a string, return the sum of the numbers appearing in the
		// string,
		// ignoring all other characters

		int sum = 0;
		StringBuilder num = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				num.append(str.charAt(i));
			} else {
				if (num.length() != 0) {
					sum += Integer.parseInt(num.toString());
				}
				num = new StringBuilder();
			}
		}
		if (num.length() != 0)
			sum += Integer.parseInt(num.toString());
		return sum;
	}

	public static boolean StringRotation(String s1, String s2) {
		// PROBLEM: Check if s2 is a rmotation of s1

		StringBuilder concatStr = new StringBuilder();
		concatStr.append(s2);
		concatStr.append(s2);
		return (concatStr.toString().contains(s1)) ? true : false;
	}

	public static void ZeroMatrix(int[][] matrix) {
		// PROBLEM: In case a zero element is found, Set its entire row and column to be
		// zero.

		// find and store zeros.
		int rowL = matrix.length;
		int colL = matrix[0].length;
		Boolean[] zeroRows = new Boolean[rowL];
		Boolean[] zeroCols = new Boolean[colL];

		Arrays.fill(zeroRows, false);
		Arrays.fill(zeroCols, false);

		for (int i = 0; i < rowL; i++) {
			for (int j = 0; j < colL; j++) {
				if (matrix[i][j] == 0) {
					zeroRows[i] = true;
					zeroCols[j] = true;
				}
			}
		}

		for (int i = 0; i < rowL; i++) {
			if (zeroRows[i])
				nullifyRow(matrix, i);
		}

		for (int i = 0; i < colL; i++) {
			if (zeroCols[i])
				nullifyCol(matrix, i);
		}

		/*
		 * Test:
		 * 
		 * for(int i=0;i<matrix.length;i++) { for(int j=0;j<matrix[i].length;j++) {
		 * System.out.println(matrix[i][j]); } }
		 */
	}

	private static void nullifyRow(int[][] matrix, int row) {
		for (int i = 0; i < matrix[0].length; i++)
			matrix[row][i] = 0;
	}

	private static void nullifyCol(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++)
			matrix[i][col] = 0;
	}

	public static void TransposeMatrix(int[][] matrix) {
		// PROBLEM: Transpose of N*N matrix
		// e.g: matrix = {{1,2,3},{4,5,6},{7,8,9}};

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}

		/*
		 * for(int i=0;i<matrix.length;i++) { for(int j=0;j<matrix[i].length;j++) {
		 * System.out.println(matrix[i][j]); } }
		 */
	}

	public static void swap(int i, int j) {
		int temp;
		temp = i;
		i = j;
		j = temp;
	}

	public static String StringCompression(String str) {
		// PROBLEM: aaabc -> a3b1c1. Return original string if length of compressed
		// string is more.
		String s = str + "\0";
		StringBuilder compStr = new StringBuilder();
		int count = 1;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				count++;
			} else {
				compStr.append(s.charAt(i));
				compStr.append(count);
				count = 1;
			}
		}
		return (s.length() > compStr.length()) ? compStr.toString() : str;
	}

	public static Boolean OneAway(String s1, String s2) {
		// PROBLEM: One edit away. A edit can be insert or remove or replace
		// e.g: s1 = pale, s2 = bale (pales) Ans. True

		int l1 = s1.length();
		int l2 = s2.length();

		if (Math.abs(l1 - l2) > 1)
			return false;

		int[] freq = new int[26];

		for (int i = 0; i < l1; i++) {
			freq[s1.charAt(i) - 'a']++;
		}

		for (int i = 0; i < l2; i++) {
			if (freq[s2.charAt(i) - 'a'] == 0)
				continue;
			freq[s2.charAt(i) - 'a']--;
		}

		int edits = 0;
		for (int i = 0; i < freq.length; i++) {
			System.out.println(freq[i]);
			if (freq[i] != 0)
				edits++;
		}

		return (edits <= 1) ? true : false;
	}

	public static Boolean PalindromePermutation(String s) {
		// PROBLEM: Check if the permutation of string can form a palindrome
		// e.g: Taco cat -> cato tac. Hence True.

		s = s.toLowerCase();
		int[] freq = new int[26];
		for (int i = 0; i < s.length(); i++) {
			// System.out.println(s.charAt(i));
			if (s.charAt(i) == ' ')
				continue;
			freq[s.charAt(i) - 'a']++;
		}

		Boolean flag = false;
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] % 2 == 1 && flag == false) {
				flag = true;
			} else if (freq[i] % 2 == 1 && flag == false) {
				return false;
			}
		}
		return true;
	}

	public static String URLify(String str) {
		// PROBLEM: Replace spaces with %20
		// e.g: str = "Hello World ";
		int trueLength = 11;
		char[] c = str.toCharArray();

		int spaceCount = 0;
		for (int i = 0; i < trueLength; i++) {
			if (c[i] == ' ') {
				spaceCount++;
			}
		}
		int index = trueLength + spaceCount * 2;

		for (int i = trueLength - 1; i >= 0; i--) {
			if (c[i] == ' ') {
				c[index - 1] = '0';
				c[index - 2] = '2';
				c[index - 3] = '%';
				index -= 3;
			} else {
				c[index - 1] = c[i];
				index--;
			}
		}

		return new String(c);
	}

}
