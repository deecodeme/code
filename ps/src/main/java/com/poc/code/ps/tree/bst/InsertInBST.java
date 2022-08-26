package com.poc.code.ps.tree.bst;

import com.poc.code.ds.tree.TreeNode;

public class InsertInBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            return newNode;
        }
        TreeNode head = root;
        while (root != null) {
            if (val > root.val) {
                if (root.right == null) {
                    root.right = newNode;
                    break;
                } else {
                    root = root.right;
                }
            } else {
                if (root.left == null) {
                    root.left = newNode;
                    break;
                } else {
                    root = root.left;
                }
            }
        }
        return head;
    }

    public TreeNode insertIntoBSTRecursive(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right = insertIntoBSTRecursive(root.right, val);
        } else {
            root.left = insertIntoBSTRecursive(root.left, val);
        }
        return root;
    }
}
