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
     * Example 1:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     * Example 2:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 2
     * Output: -1
     * Explanation: 2 does not exist in nums so return -1
     *
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
     * Given an integer array nums and an integer val, remove all occurrences（出现） of val in nums in-place.
     * The relative order of the elements may be changed.
     * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
     * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
     * It does not matter what you leave beyond the first k elements.
     * Return k after placing the final result in the first k slots of nums.
     *----------------------------------------------------------------------------------------------------------------------------
     * Example 1:
     *
     * Input: nums = [3,2,2,3], val = 3
     * Output: 2, nums = [2,2,_,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     * ----------------------------------------------------------------------------------------------------------------------------
     * Example 2:
     *
     * Input: nums = [0,1,2,2,3,0,4,2], val = 2
     * Output: 5, nums = [0,1,4,0,3,_,_,_]
     * Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
     * Note that the five elements can be returned in any order.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    // 可以用快慢指针，或者 双向指针
    public static int removeElement(int[] nums, int val) {
        // 快慢指针
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
     * 双指针：  快指针-指向新数所需要的第一个元素   慢指针-需要更新数组的位置
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement2(int[] nums, int val) {
        // 双向指针
        int left = 0;
        int right = nums.length - 1;
        while(right >= 0 && nums[right] == val) right--; //将right移到从右数第一个值不为val的位置
        while(left <= right) {
            if(nums[left] == val) { //left位置的元素需要移除
                //将right位置的元素移到left（覆盖），right位置移除
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
     * Example 1:
     * Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     *
     * Example 2:
     * Input: nums = [-7,-3,2,3,11]
     * Output: [4,9,9,49,121]
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
        // 双指针法
        int l = 0;
        int r = nums.length - 1;
        int[] res = new int[nums.length];
        int j = nums.length - 1;
        while (l <= r) {
            if (nums[l] * nums[l] > nums[r] * nums[r]) {
                res[j] = nums[l] * nums[l];
                l++;
            } else {
                res[j] = nums[r] * nums[r];
                r--;
            }
            j--;
        }
        return res;
    }

    /**
     * 209. Minimum Size Subarray Sum
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a
     * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     *
     *
     * Example 1:
     * Input: target = 7, nums = [2,3,1,2,4,3]
     * Output: 2
     * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
     *
     * Example 2:
     * Input: target = 4, nums = [1,4,4]
     * Output: 1
     *
     * Example 3:
     * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
     * Output: 0
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口
        int left = 0; // 滑动窗口起始位置
        int sum = 0; // 滑动窗口数值之和
        int result = Integer.MAX_VALUE; // 滑动窗口的长度
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            // 注意这里使用while，每次更新 i（起始位置），并不断比较子序列是否符合条件
            while (sum >= target) {
                // 取子序列的长度
                result = Math.min(result, right - left + 1); // 从左到右压缩窗口 Compressing the window from left to right
                sum = sum - nums[left]; // 这里体现出滑动窗口的精髓之处，不断变更i（子序列的起始位置）
                left++;
            }
        }
        // 如果result没有被赋值的话，就返回0，说明没有符合条件的子序列
        return result == Integer.MAX_VALUE ? 0 : result;

    }

    /**
     * 59. Spiral Matrix II
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     * Example 1:
     *    ｜1｜2｜3｜
     *    ｜8｜9｜4｜
     *    ｜7｜6｜5｜
     * Input: n = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     * Example 2:
     *
     * Input: n = 1
     * Output: [[1]]
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {

        // 循环次数
        int loop = 0;
        // 返回矩阵
        int[][] result = new int[n][n];
        // 每次循环的点
        int start = 0;
        // 填充值
        int count = 1;
        int i, j;
        // loop = n / 2 每个圈循环几次，例如n为奇数3，那么loop = 1 只是循环一圈，矩阵中间的值需要单独处理
        while (loop++ < n / 2) { // 判断循环几圈，loop从1开始

            // 下面开始的四个for就是模拟转了一圈

            // 上面：从左到右
            for (j = start; j < n - loop; j++) { // 左闭右开，每循环一次，右边就少一个不用填
                result[start][j] = count++;
            }
            // 右边：从上到下
            for (i = start; i < n - loop; i++) {
                result[i][j] = count++;
            }
            // 下面：从右到左
            for (; j >= loop; j--) {
                result[i][j] = count++;
            }
            // 左面：从下到上
            for (; i >= loop; i--) {
                result[i][j] = count++;
            }
            start++;
        }

        //  矩阵中间的位置，例如：n为3， 中间的位置就是(1，1)，n为5，中间位置为(2, 2)
        if (n % 2 == 1) {
            result[start][start] = n * n;
        }

        return result;

    }
}
