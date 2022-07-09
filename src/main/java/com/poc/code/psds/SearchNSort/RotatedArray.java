package com.poc.code.psds.SearchNSort;

public class RotatedArray {
    public static boolean checkIfRotated(int[] arr){
        // [6, 7, 8, 1, 2, 3, 4, 5]

        int index = 0;
        while (index < arr.length - 1 && arr[index] < arr[index+1]){
            index ++;
        }

        if(arr[arr.length-1] > arr[index]){
            return false;
        }

        index++;

        for (;index < arr.length -1; index++){
            if(arr[index] > arr[index+1]){
                return false;
            }
        }

        return true;
    }

    public static int findElementInSortedRotatedArray(int[] arr, int low, int high, int element){
        // [6, 7, 8, 1, 2, 3, 4, 5]

        if(low > high){
            return -1;
        }

        int mid = (low + high)/2;

        if(arr[mid] == element){
            return mid;
        }

        if(arr[mid] < arr[high] && element > arr[mid] && element <= arr[high]){
            return findElementInSortedRotatedArray(arr, mid+1, high, element);
        }else {
            return findElementInSortedRotatedArray(arr, low, mid-1, element);
        }
    }

    public static int findInflictionPointIterative(int[] arr){
        int index = 0;
        while (index < arr.length - 1 && arr[index] < arr[index+1]){
            index ++;
        }

        return arr[index];
    }

    public static int findInflictionPointBinarySearch(int[] arr, int low, int high){
        // [9, 10, 1, 2, 3, 4, 5, 6, 7, 8]

        //[2, 3, 4, 5, 6, 7, 8, 9, 10, 1] -> low = 0, high=9

        if(low > high){
            return -1;
        }

        int mid = (low + high)/2;

        if(mid < high && arr[mid] > arr[mid+1]){
            return arr[mid];
        }

        if(mid > low & arr[mid-1] > arr[mid]){
            return arr[mid-1];
        }

        if(arr[high] < arr[mid]){
            return findInflictionPointBinarySearch(arr, mid+1, high);
        }else{
            return findInflictionPointBinarySearch(arr, low, mid-1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 8, 1, 2, 3, 4, 5};
        System.out.println("Is array rotated: " + checkIfRotated(arr));
        System.out.println("Infliction point iterative " + findInflictionPointIterative(arr));
        System.out.println("Infliction point using binary search " + findInflictionPointBinarySearch(arr, 0, arr.length-1));
        System.out.println("Element found at position " +findElementInSortedRotatedArray(arr, 0, arr.length-1, 6));
    }
}
