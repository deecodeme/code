package com.poc.code.ps.array;

/*
https://leetcode.com/problems/rotate-array/solution/
Given an array, rotate the array to the right by k steps, where k is non-negative.

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Input: nums = [1,2,3], k = 3
Output:  [1,2,3]

Input: nums = [1,2,3], k = 4
Output:  [3,1,2]
 */
public class RotateArrayFromRightByKSteps {
    public void rotateByReversing(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int t = nums[left];
            nums[left] = nums[right];
            nums[right] = t;
            left++;
            right--;
        }
    }

    /*
    Intuition: We can find out the amount of steps that every element should move

    Number of steps movement = k%nums.length

    Input
    nums = [1, 2, 3, 4, 5]  k = 2
    expected output: [4, 5, 1, 2, 3]
    move 1 to 2 steps, replacing 3
    move 3 to 2 steps, replacing 5
    move 5 to 2 steps, replacing 2
    move 2 to 2 steps, replacing 4
    move 4 to 2 steps, replacing 1
    [4, 5, 1, 2, 3]

    If I find the states, it is easier to write the function
    States: value to replace, next index

    1.
     */
    public void rotateByShiftingToSteps(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        int count = 0;
        for (int i = 0; count < nums.length; i++) {
            int prevValue = nums[i];
            int currentIndex = i;
            do {
                currentIndex = (currentIndex + k) % nums.length;
                int temp = nums[currentIndex];
                nums[currentIndex] = prevValue;
                prevValue = temp;
                count++;
            }
            while (currentIndex != i);
        }
    }
}
