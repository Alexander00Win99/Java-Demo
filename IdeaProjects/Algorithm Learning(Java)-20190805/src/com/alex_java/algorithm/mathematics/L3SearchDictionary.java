package com.alex_java.algorithm.mathematics;

import java.util.Arrays;

public class L3SearchDictionary {
    /**
     * @Descrition: search a specific word in a sorted dictionary
     * @param: dictionary - the sorted dictionary; word - the word to be found;
     * @return: boolean - whether find the word
     */
    public static boolean searchDictionary(String[] dictionary, String word) {
        if (dictionary == null) {
            return false;
        }

        if (dictionary.length == 0) {
            return false;
        }

        int left = 0, right = dictionary.length - 1, middle;

        while (left <= right) {
            middle = left + (right - left) / 2;
            if (dictionary[middle].equals(word)) {
                return true;
            } else {
                if (dictionary[middle].compareTo(word) < 0) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String[] dictionary = {"I", "am", "the", "chosen", "one", "king", "of", "kings", "and", "conqueror", "of", "conquerors"};
        String word = "I";
        System.out.println(dictionary);
        Arrays.sort(dictionary);
        System.out.println(dictionary);
        boolean bFound = L3SearchDictionary.searchDictionary(dictionary, word);
        if (bFound) {
            System.out.println(String.format("找到单词： %s", word));
        } else {
            System.out.println(String.format("未能找到单词： %s", word));
        }
    }
}
