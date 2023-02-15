package com.kang.hash;

import java.util.*;

/**
 * 哈希表的问题
 */

public class HashSolution {


    /**
     * 1.Two Sum
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = hashMap.get(target - nums[i]);
                return result;
            }
            hashMap.put(nums[i], i);
        }

        return result;
    }


    /**
     * 242. Valid Anagram
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * <p>
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {

        int[] record = new int[26];

        // String.charAt(i) 返回第i个位置上的字母
        // String.charAt(i) - 'a'  --> 返回相对于a的相对距离
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int count : record) {
            if (count != 0) {               // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
                return false;
            }
        }
        return true; // record数组所有元素都为零0，说明字符串s和t是字母异位词

    }


    /**
     * 1002. Find Common Characters
     * Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates).
     * You may return the answer in any order.
     *
     * @param words
     * @return
     */
    public List<String> commonChars(String[] words) {

        List<String> result = new ArrayList<>();
        if (0 == words.length) {
            return result;
        }
        // 用来统计所有字符串里字符出现的最小频率
        int[] hash = new int[26];
        for (int i = 0; i < words[0].length(); i++) { // 统计第一个字符串的字母出现频率
            // String.charAt(i) 返回第i个位置上的字母
            // String.charAt(i) - 'a'  --> 返回相对于a的相对距离
            hash[words[0].charAt(i) - 'a']++;
        }
        // 统计除第一个字符串外，字符出现的频率
        for (int i = 1; i < words.length; i++) {
            int[] hashOtherStr = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                hashOtherStr[words[i].charAt(j) - 'a']++;
            }
            // 更新hash，保证hash里面统计的26个字符在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
            }
        }

        // 将hash统计的字符次数，转化字符输出
        for (int i = 0; i < 26; i++) {
            while (hash[i] > 0) {
                char c = (char) (i + 'a');
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }


        return result;
    }

    /**
     * 349. Intersection of Two Arrays
     * Given two integer arrays nums1 and nums2, return an array of their intersection.
     * Each element in the result must be unique and you may return the result in any order.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();

        // 遍历nums1
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        // 遍历nums
        for (int i = 0; i < nums2.length; i++) {
            if(set.contains(nums2[i])){
                resSet.add(nums2[i]);
            }
        }
        // 将结果转化为 数组
        int[] resArr = new int[resSet.size()];
        int k = 0;
        Iterator<Integer> iterator = resSet.iterator();
        while (iterator.hasNext()){
            resArr[k] = iterator.next();
            k++;
        }

        return resArr;
    }

    /**
     * 202. Happy Number
     * Write an algorithm to determine if a number n is happy.
     * A happy number is a number defined by the following process:
     * Starting with any positive integer, replace the number by the sum of the squares of its digits.
     * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
     * Those numbers for which this process ends in 1 are happy.
     * Return true if n is a happy number, and false if not.
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        //当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }
    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            // % 取余数 5 % 3 ==> 2
            // / 取商 5/3 ==> 1
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }

}
