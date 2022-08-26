package com.poc.code.ps.SearchNSort;

public class MergeSort {
    private static void mergeSort(int[] arr, int low, int high){
        if (low <= high){
            int mid = (low + (high - low))/2;

            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);

            sortedMerge(arr, low, mid, high);
        }
    }

    private static void sortedMerge(int[] arr, int low, int mid, int high){
        // [1, 3, 5, 7, 9, 2, 4, 8, 10]
        // [5, 6, 7, 1, 2, 3, 4, 5, 6]
        int leftIndex = low;
        int rightIndex = mid+1;

        while (leftIndex <= mid && rightIndex <= high ){
            //Move while left side is smaller than right side
            while (leftIndex <= mid && arr[leftIndex] < arr[rightIndex]){
                leftIndex++;
            }

            if(leftIndex <= mid && rightIndex <= high){
                swap(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex++;
            }
        }
    }

    private static void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private static int[] mergeTwoSortedArrays(int[] A, int[] B){
        int indexA = 0;
        int indexB = 0;
        int indexC = 0;
        int[] C =  new int[A.length + B.length];

        while (indexA < A.length && indexB < B.length){
            if(A[indexA] < B[indexB]){
                C[indexC++] = A[indexA++];
            }else{
                C[indexC++] = B[indexB++];
            }
        }

        while (indexA < A.length){
            C[indexC++] = A[indexA++];
        }

        while (indexB < B.length){
            C[indexC++] = B[indexB++];
        }
        return C;
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 5, 7, 9};
        int[] B = {2, 4, 6, 8, 10};
        System.out.println("Merged sorted array: ");
        int[] C = mergeTwoSortedArrays(A, B);
        for (int i = 0; i < C.length ; i++) {
            System.out.print(C[i] + " ");
        }

        int[] unSortedArray = {9, 6, 1, 2, 10, 5, 3};
        System.out.println("\n Sort using merge sort");
        mergeSort(unSortedArray, 0, unSortedArray.length-1);
        for (int i = 0; i < unSortedArray.length ; i++) {
            System.out.print(unSortedArray[i] + " ");
        }

    }
}
