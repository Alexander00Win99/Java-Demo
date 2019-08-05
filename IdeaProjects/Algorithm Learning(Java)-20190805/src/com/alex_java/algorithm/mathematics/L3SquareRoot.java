package com.alex_java.algorithm.mathematics;

public class L3SquareRoot {
    /**
     * @Descrition: calculate the square root of a specific number
     * @param: num - the number to be calculated; maxTry - the maxinum times to try; deltaThreshold - the threshold to stop the iteration;
     * @return: double - the result
     */
    public static double getSquareRoot(double num, int maxTry, double deltaThreshold) {
        double min, middle, max, square, delta;
        if (num <= 1) {
            min = num;
            max = 1.0;
        } else {
            min = 1.0;
            max = num;
        }
        for (int i = 0; i < maxTry; i ++) {
            middle = (min + max) / 2;
            square = Math.pow(middle, 2);
            delta = Math.abs(square / num - 1);
            if (delta <= deltaThreshold) {
                return middle;
            } else {
                if (square < num) {
                    min = middle;
                } else {
                    max = middle;
                }
//              ((square - num) < 0) ? min = middle : max = middle;
            }
        }
        return -1.0;
    }
    public static void main(String[] args) {
        double num, res;
        num = 0.81;
        res = getSquareRoot(num, 100, 0.00001);
        if (-1.0 == res) {
            System.out.println("未找到解");
        } else {
            System.out.println(String.format("%f 的平方根是： %f", num, res));
        }
        num = 99;
        res = getSquareRoot(num, 100, 0.00001);
        if (-1.0 == res) {
            System.out.println("未找到解");
        } else {
            System.out.println(String.format("%f 的平方根是： %f", num, res));
        }
    }
}
