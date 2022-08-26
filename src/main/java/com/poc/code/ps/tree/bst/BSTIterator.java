package com.poc.code.ps.tree.bst;

import com.poc.code.ds.tree.balancedBST.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BSTIterator {
    private List<Integer> inOrderList;
    private int index = 0;

    public BSTIterator(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        this.inOrderList = new ArrayList<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            TreeNode t = stack.pop();
            this.inOrderList.add(t.val);
            if (t.right != null) {
                root = t.right;
            }
        }
    }

    public int next() {
        int val = this.inOrderList.get(this.index);
        this.index++;
        return val;
    }

    public boolean hasNext() {
        if (this.index < this.inOrderList.size()) {
            return true;
        }
        return false;
    }
}
