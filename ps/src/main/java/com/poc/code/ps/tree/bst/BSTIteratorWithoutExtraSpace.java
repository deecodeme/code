package com.poc.code.ps.tree.bst;

import com.poc.code.ds.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIteratorWithoutExtraSpace {
    private Deque<TreeNode> stack;

    public BSTIteratorWithoutExtraSpace(TreeNode root) {
        this.stack = new ArrayDeque<>();
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode t = this.stack.pop();
        int val = t.val;
        if (t.right != null) {
            t = t.right;
            while (t != null) {
                this.stack.push(t);
                t = t.left;
            }
        }
        return val;
    }

    public boolean hasNext() {
        return this.stack.isEmpty();
    }
}
