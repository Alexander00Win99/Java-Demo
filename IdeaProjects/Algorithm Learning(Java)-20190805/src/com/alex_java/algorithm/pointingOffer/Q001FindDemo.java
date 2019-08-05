package com.alex_java.algorithm.pointingOffer;

public class Q001FindDemo {
    // 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    // 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    public boolean find(int target, int[][] array) {
        for (int[] row : array)
            for (int value : row)
                if (value == target) return true;
        return false;
    }

    public boolean search(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;

        while (true) {
            if (row < 0 || col >= matrix[0].length) break; // 注意数组越界
            if (matrix[row][col] > target) {
                row --;
                continue;
            } else if (matrix[row][col] < target) {
                col ++;
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

//    public static void gradeScore(int score) {
//
//    }

    public static void main(String[] args) {
        int[][] martrix = {
                {1, 3, 9},
                {2, 9, 81},
                {4, 27, 6561}
        };
        Q001FindDemo demo = new Q001FindDemo();
        System.out.println(demo.search(martrix, 100));
    }
}
