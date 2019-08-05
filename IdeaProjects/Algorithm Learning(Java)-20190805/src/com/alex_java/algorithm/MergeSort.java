package com.alex_java.algorithm;

public class MergeSort {
    public static int[] mergeSortedArrarys(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) return arr2;
        if (arr2 == null || arr2.length == 0) return arr1;
        int[] result = new int[arr1.length + arr2.length];
        int p1 = 0, p2 = 0, p = 0;
        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] <= arr2[p2]) {
                result[p ++] = arr1[p1 ++];
            } else {
                result[p ++] = arr2[p2 ++];
            }
        }
        while (p1 < arr1.length) result[p ++] = arr1[p1 ++];
        while (p2 < arr2.length) result[p ++] = arr2[p2 ++];
        return result;
    }

    public static void merge(int[] arr, int begin, int mid, int end) {
//        if (end - begin)
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 2, 4, 6, 8};
        int[] arr2 = {1, 3, 5, 7, 9};
        int[] res = mergeSortedArrarys(arr1, arr2);
        for (int i : res) System.out.println(i);
    }
}
