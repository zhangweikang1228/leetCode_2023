package com.kang.hash;

import org.junit.Test;

public class TestHashSolution {

    @Test
    public void TestTwoSum(){

        int[] nums = {2,7,11,15};
        int target = 9;
        int[] result = HashSolution.twoSum(nums, target);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }


    }


}
