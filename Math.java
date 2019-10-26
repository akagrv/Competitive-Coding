package com.java.math;

import java.util.ArrayList;
import java.util.List;

public class Math {

	public List<String> fizzBuzz(int n) {
		List<String> ans = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0)
				ans.add("FizzBuzz");
			else if (i % 3 == 0)
				ans.add("Fizz");
			else if (i % 5 == 0)
				ans.add("Buzz");
			else
				ans.add(Integer.toString(i));
		}

		return ans;
	}

	public int countPrimes(int n) {
		int count = 0;
		for (int i = 1; i < n; i++) {
			if (isPrime(i))
				count++;
		}
		return count;
	}

	private boolean isPrime(int num) {
		if (num <= 1)
			return false;
		// Loop's ending condition is i * i <= num instead of i <= sqrt(num)
		// to avoid repeatedly calling an expensive function sqrt().
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

}
