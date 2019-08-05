package com.alex_java.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoSort {

    /***
     * 1) 排序的本质=选择+交换；
     * 2) 比较=>选择；
     * 3) 不同排序算法，不同比较方式(边比较边交换+先比较后交换)；
     * 4) Java中的ArrayList自带的sort方法本质上是冒泡排序算法；
     */

    private int getPower4People(People p) {
        int power = 0;
        if (p.age >= 65) power += 10000;
        if (p.age <= 15) power += 1000;
        if (p.sex == 0) power += 100;
        if (p.sex == 1) power += 10;
        return power;
    }

    private boolean compareInt(int a, int b) {
        if (a > b) return true;
        return false;
    }

    private boolean comparePriority4People(People a, People b) {
        int powerA = getPower4People(a);
        int powerB = getPower4People(b);
        if (powerA > powerB) return false;
        if (powerA == powerB && a.bookingTime < b.bookingTime) return false;
        return true;
    }

    public void exchangeElementByIndex(int[] arr, int indexA, int indexB) {
        arr[indexA] = arr[indexA] ^ arr[indexB];
        arr[indexB] = arr[indexA] ^ arr[indexB];
        arr[indexA] = arr[indexA] ^ arr[indexB];
    }

    /***
     * 冒泡排序：内层循环每轮结束找出极值置于末尾，因此尾部已经有序，无须参加后续循环
     * 特点：内层循环每次两两比较交换(边比较边交换)，效率不高
     * @param arr
     */
    public void bubbleSort(int[] arr) { // Unidirectional——单向递增或者单向递减
        for (int i = 0; i < arr.length; i ++)
            for (int j = 0; j < arr.length - 1 - i; j ++) // arr.length - 1 - i用于优化，其后数据已经有序，无须比较
                if (compareInt(arr[j], arr[j + 1])) exchangeElementByIndex(arr, j, j + 1); // 单向递增
                // if (compareInt(arr[j], arr[j + 1])) exchangeElementByIndex(arr, j, j + 1); // 单向递减
    }

    public void bubbleBidirectional(int[] arr, boolean flag) { // flag == true 递增Increase；flag == false 递减Decrease
        for (int i = 0; i < arr.length; i ++)
            for (int j = 0; j < arr.length - 1 - i; j ++) // arr.length - 1 - i用于优化，其后数据已经有序，无须比较
                if (flag && compareInt(arr[j], arr[j + 1]) || !flag && !compareInt(arr[j], arr[j + 1])) exchangeElementByIndex(arr, j, j + 1);
    }

    /***
     * 选择排序：内层循环每轮选出极值置于数组起始位置构成局部已排序数组，然后继续遍历未排序数组选出极值加入已排序数组
     * 特点：内层循环每次两两比较更新极值下标，循环结束交换元素(先比较后交换)
     * @param arr
     * @return arr
     */
    public int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j ++)
                if (compareInt(arr[minIndex], arr[j]))
                    minIndex = j;
            if (minIndex != i) exchangeElementByIndex(arr, i, minIndex);
        }
        return arr;
    }

    /***
     * 插入排序：基于假设前面的数列是有序数列，内层循环每轮结束会将本轮从下标位置至数组首位重新排序
     * 特点：内层循环每次两两比较交换(边比较边交换)，效率不高(优化以后，每轮先保存后交换，减少交换次数)
     */
    public void insertSort(int[] arr) {
        // (n - 1)次循环
        for (int i = 1; i < arr.length; i ++) {
            // 每次循环内部，当前索引前面部分的所有元素依次两两比较交换(效率较低)
            //for (int j = i; j > 0; j --) {
            //    if (compareInt(arr[j - 1], arr[j])) {
            //        arr[j] = arr[j] ^ arr[j - 1];
            //        arr[j - 1] = arr[j] ^ arr[j - 1];
            //        arr[j] = arr[j] ^ arr[j - 1];
            //    }
            //}
            // 进行优化，避免内层循环没有必要的两两比较交换操作，内存循环只会从后往前顺序保存大于待插元素的数组元素，
            // 直至遇到首个小于待插元素的数组元素，循环中断，当前位置放入待插元素(临界状态arr[1] > curr => arr[0] = curr)
            int currentElement = arr[i];
            int j;
            for (j = i; j > 0 && compareInt(arr[j - 1], currentElement); j --) arr[j] = arr[j - 1];
            arr[j] = currentElement;
        }
    }

    /***
     * 简单快速排序：比基准小放在左边，比基准大放右边；思想简单明了，符合人类思维
     * 特点：每层递归需要额外创建两个List资源，假如数组较大，就会消耗很多资源
     * @param list
     * @return
     */
    public List<Integer> quickSortSimple(List<Integer> list) {
        if (list.size() == 0) return list; // 临界判断，否则越界(java.lang.IndexOutOfBoundsException)

        int leader = list.get(0);
        List<Integer> res = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < list.size(); i ++) {
            if (list.get(i) < leader) {
                left.add(list.get(i)); // left此时只是顺序加入原list中所有小于leader的数，尚未有序
            } else {
                right.add(list.get(i)); // right此时只是顺序加入原list中所有小于leader的数，尚未有序
            }
        }

        res.addAll(quickSortSimple(left)); // 对left进行递归排序后合并元素
        res.add(leader);
        res.addAll(quickSortSimple(right)); // 对right进行递归排序后合并元素
        return res;
    }

    /***
     * 标准快速排序：前边空了，从右往左找，后边空，从左往右找比基准大放右边；思想简单明了，符合人类思维
     * 特点：每层递归需要额外创建两个List资源，假如数组较大，就会消耗很多资源
     * @param list
     * @return
     */
    public void quickSortStandard(int[] arr, int begin, int end) { // [begin, end)-左闭右开区间
        if (begin >= end - 1) return;
        int left = begin;
        int right = end;
        do {} while (true);
    }

    /***
     * 随机快速排序：比基准小放在左边，比基准大放右边；思想简单明了，符合人类思维
     * 特点：每层递归需要额外创建两个List资源，假如数组较大，就会消耗很多资源
     * @param list
     * @return
     */
    public void quickSortRandom(int[] arr) {

    }

    /***
     * 希尔排序 Donald Shell于1959年提出，希尔排序的时间复杂度与增量序列的选取有关，例如：希尔增量时间复杂度为O(n²)，
     * Hibbard增量的时间复杂度为O(n1.5)，希尔排序时间复杂度的下界是n*log2n。希尔排序没有快速排序算法快O(n(logn))，
     * 因此中等大小规模表现良好，对于规模巨大的数据排序不是最优选择。但是比O(n2)复杂度的算法要快得多。
     * @param arr
     * @return
     */
//    public int[] shellSort(int[] arr) {
//
//    }

    /**
     * 归并排序 利用分治思想(Divide and Conquer)
     * @param arr
     * @return
     */
    public int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        // 递归拆分，直至left和right都是只含一个元素
        int[] a = this.mergeSort(left);
        int[] b = this.mergeSort(right);
        return this.merge(a, b);
        // return this.merge(this.mergeSort(left), this.mergeSort(right));
    }

    private int[] merge(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int index = 0;
        int[] arr = new int[a.length + b.length];
        for (int icdex = 0; index < a.length + b.length; index++) {
            if (i >= a.length) {
                arr[index] = b[j++];
            } else if (j >= b.length) {
                arr[index] = a[i++];
            } else if (a[i] <= b[j]) {
                arr[index] = a[i++];
            } else {
                arr[index] = b[j++];
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        DemoSort demo = new DemoSort();
        int arr[] = {9, 2, 7, 8, 1, 3, 6, 5, 4, 0};
        int[] arrBublble, arrInsert, arrQuick, arrShell, arrMerge;

        People a = new People("a", 36, 0, 7.5);
        People b = new People("b", 81, 1, 7.0);
        People c = new People("c", 25, 0, 8.0);
        People d = new People("d", 16, 1, 8.5);
        People e = new People("e", 9, 0, 10.0);
        People f = new People("f", 49, 1, 8.5);
        People g = new People("g", 64, 0, 7.0);

        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(7);
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(0);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);
        for (int i : list) System.out.println(i);

        List<Integer> result = demo.quickSortSimple(list);
        for (int i : result) System.out.println(i);

    }
}

class People {
    String name;
    int age;
    int sex;
    double bookingTime;
    public People(String name, int age, int sex, double bookingTime) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.bookingTime = bookingTime;
    }
}
