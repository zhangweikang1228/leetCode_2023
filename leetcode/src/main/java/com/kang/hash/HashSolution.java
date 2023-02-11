package com.kang.hash;

import java.util.HashMap;
import java.util.Map;

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
            if(hashMap.containsKey(target - nums[i])){
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
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
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
        for (int count: record) {
            if (count != 0) {               // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
                return false;
            }
        }
        return true; // record数组所有元素都为零0，说明字符串s和t是字母异位词

    }



}
