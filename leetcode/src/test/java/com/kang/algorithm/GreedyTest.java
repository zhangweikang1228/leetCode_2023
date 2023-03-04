package com.kang.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreedyTest {

    Greedy greedy = new Greedy();
    @Test
    public void maxProfit() {
        int[] sums = {1,2,3,4,5};
        greedy.maxProfit(sums);
    }

    @Test
    public void canJump() {
        int[] nums = {2,3,1,1,4};
        greedy.canJump(nums);
    }
}