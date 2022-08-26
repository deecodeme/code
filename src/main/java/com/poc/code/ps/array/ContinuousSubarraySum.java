package com.poc.code.ps.array;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (j - i >= 1 && sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
