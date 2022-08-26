package com.poc.code.ps.tree;

import com.poc.code.ds.tree.balancedBST.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTUtil(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBSTUtil(root.left, min, root.val) && isValidBSTUtil(root.right, root.val, max);
    }

    public boolean isValidBSTIterative(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long min = Long.MAX_VALUE;
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            TreeNode t = stack.pop();
            if (t.val <= min) {
                return false;
            } else {
                min = t.val;
            }
            // get the element
            if (t.right != null) {
                root = t.right;
            }
        }
        return true;
    }

}
