package com.alex_java.algorithm.pointingOffer;

public class Q002ReplaceSpace {
    // 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
    public static String replaceSpace(StringBuffer str) {
        return str.toString().replace("", "%20");
    }

    public static String replaceSpace(String str, char substitue) {
        char[] chars = new char[str.length() * 3];
        int p = 0;

        for (int i = 0; i < str.length(); i ++) {
            if (str.charAt(i) != substitue) {
                chars[p] = str.charAt(i);
                p ++;
            } else {
                chars[p] = '%';
                chars[p + 1] = '2';
                chars[p + 2] = '0';
                p += 3;
            }
        }
        return new String(chars, 0, p);
    }

    public static void main(String[] args) {
        String str = "We Are Happy";
        System.out.println(str);
        System.out.println(str.toString());
        System.out.println(replaceSpace(str, ' '));
    }
}
