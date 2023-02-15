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
            if (set.contains(nums2[i])) {
                resSet.add(nums2[i]);
            }
        }
        // 将结果转化为 数组
        int[] resArr = new int[resSet.size()];
        int k = 0;
        Iterator<Integer> iterator = resSet.iterator();
        while (iterator.hasNext()) {
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
     *
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


    /**
     * 454. 4Sum II
     * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> hashmap = new HashMap<>();

        // 统计两个数组中的元素之和，同时计算出现的次数，放入map
        for (int i : nums1) {
            for (int j : nums2) {
                if (hashmap.containsKey(i + j)) {
                    hashmap.put(i + j, hashmap.get(i + j) + 1);
                } else {
                    hashmap.put(i + j, 1);
                }
            }
        }
        // 统计剩余的两个元素的和，在hashmap中寻找 是否存在相加为0的情况，同时记录次数
        for (int i : nums3) {
            for (int j : nums4) {
                if (hashmap.containsKey(0 - (i + j))) {
                    res = res + hashmap.get(0 - (i + j));
                }
            }

        }

        return res;
    }

    /**
     * 383. Ransom Note 赎金票据
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {

        boolean res = true;
        int[] record = new int[26];

        for (int i = 0; i < magazine.length(); i++) {
            record[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            record[ransomNote.charAt(i) - 'a']--;
        }

        for (int i : record) {
            if (i < 0) {
                res = false;
            }
        }

        return res;

    }


    /**
     * 15. 3Sum
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
     * and nums[i] + nums[j] + nums[k] == 0.
     * Notice that the solution set must not contain duplicate triplets.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 先对nums排序
        Arrays.sort(nums);
        // 找a+b+c=0
        // a=nums[i] b=nums[left] c=nums[right]
        for (int i = 0; i < nums.length; i++) {
            // 排序之后如果第一个元素已经大于0，那么无论怎么组合都不会出现3元组，直接返回
            if (nums[i] > 0) {
                return result;
            }
            // 去重a
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else { // sum = 0
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重逻辑放到找到一个三元组之后，对b 和 c 去重
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }
        }


        return result;
    }

    /**
     * 用set解决 3数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if (nums.length == 0) {
            return new ArrayList<>(res);
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) res.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                else if (sum > 0) k--;
                else if (sum < 0) j++;
            }

        }
        return new ArrayList<>(res);

    }


    /**
     * 18. 4Sum
     * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
     * 0 <= a, b, c, d < n
     * a, b, c, and d are distinct.
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * You may return the answer in any order.
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 本题在三数只和的基础上，再在外层套一层循环
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            // nums[i] > target 直接返回, 剪枝操作
            if (nums[i] > 0 && nums[i] > target) {
                return result;
            }

            if (i > 0 && nums[i - 1] == nums[i]) {    // 对nums[i]去重
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {

                if (j > i + 1 && nums[j - 1] == nums[j]) {  // 对nums[j]去重
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;
                while (right > left) {
                    // nums[k] + nums[i] + nums[left] + nums[right] > target int会溢出
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 对nums[left]和nums[right]去重
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;

                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }


}
