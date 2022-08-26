package com.poc.code.ps.tree.bst;

import com.poc.code.ds.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        boolean flag = false;
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            TreeNode t = stack.pop();
            if (flag) {
                return t;
            }
            if (t == p) {
                flag = true;
            }
            if (t.right != null) {
                root = t.right;
            }
        }
        return null;
    }

    /*
    TODO
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        return null;
    }
}
