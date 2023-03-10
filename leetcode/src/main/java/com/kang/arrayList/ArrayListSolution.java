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
    // ?????????????????????????????? ????????????
    public static int removeElement(int[] nums, int val) {
        // ????????????
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    /**
     * ????????????  ?????????-???????????????????????????????????????   ?????????-???????????????????????????
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement2(int[] nums, int val) {
        // ????????????
        int left = 0;
        int right = nums.length - 1;
        while(right >= 0 && nums[right] == val) right--; //???right?????????????????????????????????val?????????
        while(left <= right) {
            if(nums[left] == val) { //left???????????????????????????
                //???right?????????????????????left???????????????right????????????
                nums[left] = nums[right];
                right--;
            }
            left++;
            while(right >= 0 && nums[right] == val) right--;
        }
        return left;
    }

    /**
     * 977. Squares of a Sorted Array
     * Given an integer array nums sorted in non-decreasing order,
     * return an array of the squares of each number sorted in non-decreasing order.
     *
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
        // ????????????
        int l = 0;
        int r = nums.length - 1;
        int[] res = new int[nums.length];
        int j = nums.length - 1;
        while (l <= r) {
            if (nums[l] * nums[l] > nums[r] * nums[r]) {
                res[j--] = nums[l] * nums[l++];
            } else {
                res[j--] = nums[r] * nums[r--];
            }
        }
        return res;
    }

    /**
     * 209. Minimum Size Subarray Sum
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a
     * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        // ????????????
        int left = 0; // ????????????????????????
        int sum = 0; // ????????????????????????
        int result = Integer.MAX_VALUE; // ?????????????????????
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                result = Math.min(result, right - left + 1); // ???????????????????????? Compressing the window from left to right
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;

    }

    /**
     * 59. Spiral Matrix II
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {

        // ????????????
        int loop = 0;
        // ????????????
        int[][] result = new int[n][n];
        // ??????????????????
        int start = 0;
        // ?????????
        int count = 1;
        int i, j;
        while (loop++ < n / 2) { // ?????????????????????loop???1??????
            // ?????????????????????
            for (j = start; j < n - loop; j++) { // ????????????????????????????????????????????????????????????
                result[start][j] = count++;
            }
            // ?????????????????????
            for (i = start; i < n - loop; i++) {
                result[i][j] = count++;
            }
            // ?????????????????????
            for (; j >= loop; j--) {
                result[i][j] = count++;
            }
            // ?????????????????????
            for (; i >= loop; i--) {
                result[i][j] = count++;
            }
            start++;
        }
        if (n % 2 == 1) {
            result[start][start] = n * n;
        }

        return result;

    }
}
