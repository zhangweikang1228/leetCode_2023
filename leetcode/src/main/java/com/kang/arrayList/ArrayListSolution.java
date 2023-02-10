package com.kang.arrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListSolution {


    /**
     * 56.Merge Intervals
     * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
     * and return an array of the non-overlapping intervals that cover all the intervals in the input.
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }


    /**
     * 704. Binary Search
     * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums.
     * If target exists, then return its index. Otherwise, return -1.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {

        int high = nums.length;
        int low = 0;
        int index = high / 2;
        if (target > nums[high - 1] || target < nums[0]) {
            return -1;
        }

        while (low <= high) {
            if (target == nums[index]) {
                return index;
            } else if (target > nums[index]) {
                low = index + 1;
            } else {
                high = index - 1;
            }
            index = (low + high) / 2;
        }
        return -1;
    }

    /**
     * 27. Remove Element
     * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
     * The relative order of the elements may be changed.
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
     * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
     * It does not matter what you leave beyond the first k elements.
     * Return k after placing the final result in the first k slots of nums.
     */
    // 可以用快慢指针，或者 双向指针
    public static int removeElement(int[] nums, int val) {

        int temp = 0;

        for (int i = 0; i < nums.length; i++) {
            if (val == nums[i]) {
                temp++;
            } else {
                nums[i-temp] = nums[i];
            }
        }
        for (int j = 0; j < nums.length; j++) {
            System.out.println(nums[j]);
        }




        return temp;
    }

    /**
     * 977. Squares of a Sorted Array
     * Given an integer array nums sorted in non-decreasing order,
     * return an array of the squares of each number sorted in non-decreasing order.
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
/*        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }

        Arrays.sort(result);

        return result;*/
        // 双指针法
        int l = 0;
        int r = nums.length - 1;
        int[] res = new int[nums.length];
        int j = nums.length - 1;
        while(l <= r){
            if(nums[l] * nums[l] > nums[r] * nums[r]){
                res[j--] = nums[l] * nums[l++];
            }else{
                res[j--] = nums[r] * nums[r--];
            }
        }
        return res;
    }

}
