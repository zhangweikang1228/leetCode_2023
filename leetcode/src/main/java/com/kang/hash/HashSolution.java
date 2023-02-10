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



}
