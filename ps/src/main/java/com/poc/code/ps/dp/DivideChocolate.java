package com.poc.code.ps.dp;

import java.util.stream.IntStream;

/*
https://leetcode.com/problems/divide-chocolate/
You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts,
 each piece consists of some consecutive chunks.
Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.

Example 1:
Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

Problem Solving
1. chunk = [1], k = 0 -> chunk
2. chunks=[1,2], k =0 -> sum(sweetness)
3. chunks=[1,2], k =1 -> Min()
4. chunks=[1,2,3], k = 1 -> Max(Min(1), (2,3)), Min((1,2),(3))

f(i, k) -> maximum sweetness for k+1 partition for start index i
        ->

base case-
if k == len(sweetness) -> return min of all elements
if k == 0 -> return sum of all elements

2,3,4,5,6,7,8,9  l = 8
 k = 4
 */
// TODO: Not yet done
public class DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int k) {
        return maximizeSweetnessDpUtil(sweetness, k, 0);
    }

    public int maximizeSweetnessDpUtil(int[] sweetness, int k, int index) {
        if (k == 0) {
            return IntStream.range(index, sweetness.length - 1).map(i -> sweetness[i]).sum();
        }

        if (k == sweetness.length - 1) {
            return IntStream.range(index, sweetness.length - 1).map(i -> sweetness[i]).min().getAsInt();
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = index; i < sweetness.length - k; i++) {
            sum += sweetness[i];
            max = Math.max(max, Math.min(sum, maximizeSweetnessDpUtil(sweetness, k - 1, i + 1)));
        }
        return max;
    }

}
