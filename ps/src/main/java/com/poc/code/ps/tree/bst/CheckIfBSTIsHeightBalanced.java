package com.poc.code.ps.tree.bst;

import com.poc.code.ds.tree.TreeNode;

public class CheckIfBSTIsHeightBalanced {
    public boolean isBalanced(TreeNode root) {
        int r = isBalancedUtil(root);
        if (r == -1) {
            return false;
        }
        return true;
    }

    private int isBalancedUtil(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = isBalancedUtil(root.left);
        int r = isBalancedUtil(root.right);
        if (l == -1 || r == -1) {
            return -1;
        }
        if (Math.abs(l - r) > 1) {
            return -1;
        }
        return Math.max(l, r) + 1;
    }
}
