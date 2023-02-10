package com.kang;

public class Main {
    public static void main(String[] args) {
        int[][] nums = {{1,3},{2,6},{8,10},{15,18}};
        int[] newNums = nums[0];

        System.out.println(nums.length);
        System.out.println(nums[0].length);
        System.out.println(nums[0][0]);
        System.out.println(newNums.length);
        System.out.println(newNums[0] + "" +newNums[1]);
    }
}