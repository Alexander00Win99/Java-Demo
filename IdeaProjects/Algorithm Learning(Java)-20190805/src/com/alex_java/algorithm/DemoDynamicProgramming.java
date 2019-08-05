package com.alex_java.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DemoDynamicProgramming {
    public static int lds(int[] arr, int begin, int end, int maxValue) { // 指定范围[begin, end)左闭右开区间
        if (arr == null || arr.length == 0) return 0;

        int maxLength = 0;
//        for (int i = begin; i < end; i ++) { // 鉴于高射炮技术缺陷，当前可以拦截导弹的飞行高度只能低于前发已拦截导弹的飞行高度，否则，无法拦截
//            int subArr = new int[arr.length - i - 1]; // 假设从第i发导弹已被拦截，那么下一发被拦截导弹只可能是后续子序列(长度是arr.length - i -1)中的某一个。
//            System.arraycopy(arr, i + 1, subArr, 0, arr.length - i - 1);
//        }
        for (int i = begin; i < end; i ++) {
            if (arr[i] < maxValue) { // 假设当前导弹飞行高度为arr[i]小于前发已拦截导弹飞行高度才能继续累加后续导弹子序列
                int maxSubLength = lds(arr, i + 1, end, arr[i]);
                maxLength = Math.max(maxLength, maxSubLength + 1);
            }
        }
        return maxLength;
    }

    public static List<List<Integer>> ldsSet(int[] arr, int begin, int end, int maxValue) {
        if (arr == null || arr.length == 0) return Collections.emptyList();
        int maxLength = 0;
        List<List<Integer>> listSet = new ArrayList<>();

        if (begin >= end) { // 递归出口：在空的二维链表中插入空的一维链表
            listSet.add(new ArrayList<>());
            return listSet;
        }

        for (int i = begin; i < end; i ++) { // 对于[begin, end)之间的每个元素arr[i]，求其后向(后向==[i + 1, end)区间)最长递减子序列(只有当arr[i] < maxValue时，当前元素才有可能有子序列，并且子序列的maxVlaue是当前元素以此保证当前元素和后向子序列同样可以合并成为最长递减子序列)
            if (arr[i] < maxValue) { // 先决条件：当前元素不能高于前发拦截高度
                List<List<Integer>> subSet = ldsSet(arr, i + 1, end, arr[i]);
                for (List<Integer> list : subSet) {
                    list.add(0, i); // i是当前元素的下标索引，使用下标i代表当前元素，可以有效保证序列展示唯一性
                    if (list.size() == maxLength) listSet.add(list);
                    if (list.size() > maxLength) {
                        listSet.clear();
                        listSet.add(list);
                        maxLength = list.size();
                    }
                }
            }
        }
        return listSet;
    }

    public static int bagProblem(int bagSize, int[][] goodsArr) { // 穷举法：尝试装入各个物品。弊端：当数据量大时，容易导致栈溢出。
        int maxValueSum = 0;
        // 尝试放入任何物品
        for (int[] temp : goodsArr) {
            int space = temp[0];
            int value = temp[1];
            if (bagSize >= space) { // 递归出口：bagSize < space => return 0
                int tempValue = bagProblem(bagSize - space, goodsArr);
                maxValueSum = Math.max(maxValueSum, value + tempValue);
            }
        }
        return maxValueSum;
    }

    public static int[] getMVPGoods(int[][] goodsArr) { // 求取单价最高物品，返回对应下标，当可以使用贪心算法时，直接装入对应下标物品即可
        int index = 0;
            for (int i = 1; i < goodsArr.length; i ++) {
                if (goodsArr[i][1] / goodsArr[i][0] > goodsArr[index][1] / goodsArr[index][0]) index = i;
                if (goodsArr[i][1] / goodsArr[i][0] == goodsArr[index][1] / goodsArr[index][0] && goodsArr[i][1] % goodsArr[i][0] > goodsArr[index][1] % goodsArr[index][0]) index = i;
            }
        return goodsArr[index];
    }

    public static int getSumSize(int[][] goodsArr) { // 求取各种物品空间之和，作为判断能否使用贪心算法的阈值
        int sumSize = 0;
        for (int[] temp : goodsArr) sumSize += temp[0];
        return sumSize;
    }

    public static int luggageProblem(int bagSize, int[][] goodsArr) { // 贪心算法 + 穷举法：当背包空间足够容纳最大性价比物品时，使用贪心算法装入，之后在剩余空间上再行使用穷举法装入物品，优点：避免栈溢出。
        int maxValueSum = 0;
        int maxGreedySum = 0;
        // 当背包空间足够容纳所有物品还有剩余时，在多余空间上使用贪心算法直接装入单价最高物品
        if (bagSize > getSumSize(goodsArr)) {
            int[] mvpGoods = getMVPGoods(goodsArr);
            int greedySpace = bagSize - getSumSize(goodsArr);
            maxGreedySum += greedySpace / mvpGoods[0] * mvpGoods[1];
            bagSize = getSumSize(goodsArr) + greedySpace % mvpGoods[0];
        }
        // 使用贪心算法放完最贵物品之后再行尝试放入任何物品
        for (int[] temp : goodsArr) {
            int space = temp[0];
            int value = temp[1];
            if (bagSize >= space) { // 递归出口：bagSize < space => return 0
                int tempValue = luggageProblem(bagSize - space, goodsArr);
                maxValueSum = Math.max(maxValueSum, value + tempValue);
            }
        }
        maxValueSum += maxGreedySum;
        return maxValueSum;
    }



    public static void main(String[] args) {
//        int[] arr = new int[]{9, 8, 7, 3, 2, 1, 6, 5, 4, 3, 2, 1};
        int[] arr = new int[]{4, 3, 7, 4, 2, 1};
        int maxLength = lds(arr, 0, arr.length, Integer.MAX_VALUE);
        System.out.println(maxLength);

/* 背包问题
        int[][] goodsArr = {{1, 10}, {2, 22,}, {3, 45}};
        int maxValueSum = 0;
        long begin = 0;
        long end = 0;

        begin = System.currentTimeMillis();
        maxValueSum = bagProblem(36, goodsArr);
        end = System.currentTimeMillis();
        System.out.println("用时： " + (end - begin));
        System.out.println("最佳装包价值： " + maxValueSum);

        begin = System.currentTimeMillis();
        maxValueSum = luggageProblem(100, goodsArr);
        end = System.currentTimeMillis();
        System.out.println("用时： " + (end - begin));
        System.out.println("最佳装包价值： " + maxValueSum);
背包问题 */


    }
}
