package com.poc.code.ds;

import java.util.stream.IntStream;

public class DisjointSet {
    private int[] root;
    private int[] rank;

    public DisjointSet(int size) {
        this.root = new int[size];
        this.rank = new int[size];
        IntStream.range(1, size).forEach(i -> {
            root[i] = i;
            rank[i] = 0;
        });
    }

    /*
    Time Complexity: O(k)
    Space Complexity: O(2N)
     */
    public int find(int a) {
        if (this.root[a] == a) {
            return a;
        }
        int p = find(this.root[a]);
        this.root[a] = p;
        return p;
    }

    /*
    Time Complexity: O(k)
    Space Complexity: O(2N)
 */
    public void union(int a, int b) {
        int p1 = this.find(a);
        int p2 = this.find(b);
        if (p1 != p2) {
            if (rank[p1] == rank[p2]) {
                this.root[p2] = p1;
            } else if (rank[p2] > rank[p1]) {
                this.root[p1] = p2;
            } else {
                this.root[p2] = p1;
                rank[p1] = 1;
            }
        }
    }

    public boolean isInSameSet(int a, int b) {
        return this.find(a) == this.find(b);
    }
}
