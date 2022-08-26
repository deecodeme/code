package com.poc.code.ps.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/intersection-of-two-arrays-ii/solution/
 */
public class IntersectionOfTwoArrays {
    public int[] intersectBySorting(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    //TODO it should work using merge sort strategy
    public int[] intersectBySortingAndDivideConquer(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int l1 = nums1.length;
        int l2 = nums2.length;
        // choose a middle element and break the array into two halves and then find intersection parallely
        return new int[]{};
    }

    public int[] intersectMerge(int[] nums1, int[] nums2, int l1, int l2, int h1, int h2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[]{};
        } else {
            int indexNums1 = findIndex(nums1, l1, h1);
            int indexNums2 = findFirstElementJustGreaterOrEqual(nums2, l2, h2, nums1[indexNums1]);
            int[] left = intersectMerge(nums1, nums2, l1, indexNums1 - 1, l2, indexNums2 - 1);
            int[] right = intersectMerge(nums1, nums2, indexNums1, h1, indexNums2, h2);
            //merge and result and return
        }
        return new int[]{};
    }

    /*
    Find an index considering the duplicated on the left side
     */
    public int findIndex(int[] nums, int low, int high) {
        int mid = low + (high - low) / 2;
        while (mid < high && nums[mid] == nums[mid + 1]) {
            mid++;
        }
        return mid + 1;
    }

    public int findFirstElementJustGreaterOrEqual(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[high] == target ? high : -1;
    }
}
