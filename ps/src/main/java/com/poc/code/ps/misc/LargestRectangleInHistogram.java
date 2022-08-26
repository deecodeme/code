package com.poc.code.ps.misc;

public class LargestRectangleInHistogram {
    public int largestAreaInHistogram(int[] h) {
        int[] minFromLeft = new int[h.length];
        int[] minFromRight = new int[h.length];

        minFromLeft[0] = h[0];
        for (int i = 1; i < h.length; i++) {
            if (h[i] < minFromLeft[i - 1]) {
                minFromLeft[i] = h[i];
            } else {
                minFromLeft[i] = minFromLeft[i - 1];
            }
        }

        minFromRight[h.length - 1] = h[h.length - 1];
        for (int i = h.length - 1; i >= 0; i--) {
            if (h[i] < minFromRight[i + 1]) {
                minFromRight[i] = h[i];
            } else {
                minFromRight[i] = minFromRight[i + 1];
            }
        }

        int maxArea = h[0];
        for (int i = 1; i < h.length; i++) {
            int left = findJustSmallerInDecrArray(h, 0, i - 1, h[i]);
            int right = findJustSmallerInDecrArray(h, i + 1, h.length - 1, h[i]);
            if (left == -1) {
                left = 0;
            } else {
                left = left + 1;
            }
            if (right == -1) {
                right = h.length - 1;
            } else {
                right = right + 1;
            }
            if (h[i] * (i - left + 1) * (right - i + 1) > maxArea) {
                maxArea = h[i] * (i - left) * (right - i);
            }

        }
        return maxArea;
    }

    public int findJustSmallerInDecrArray(int[] A, int low, int high, int k) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (A[mid] >= k){
                low = mid+1;
            }else{

            }
        }
        return -1;
    }
}
