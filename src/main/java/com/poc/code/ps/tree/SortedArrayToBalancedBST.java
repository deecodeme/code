package com.poc.code.ps.tree;

import com.poc.code.ds.tree.balancedBST.TreeNode;

/*
https://leetcode.com/explore/learn/card/introduction-to-data-structure-binary-search-tree/143/appendix-height-balanced-bst/1015/
Given an integer array nums where the elements are sorted in ascending order,
convert it to a height-balanced binary search tree.

Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Input: nums = [1,3]
Output: [3,1]
Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 */
public class SortedArrayToBalancedBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTUtil(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTUtil(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int m = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = sortedArrayToBSTUtil(nums, left, m - 1);
        root.right = sortedArrayToBSTUtil(nums, m + 1, right);
        return root;
    }
}
