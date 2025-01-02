package com.kang.arrayList;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;

public class ArrayListSolutionTest {

    @Test
    public void testMerge() {
        int[][] nums = {{1, 3}, {2, 6}, {8, 10}, {15, 18}, {10, 20}};
        int[][] newNums = ArrayListSolution.merge(nums);
        for (int i = 0; i < newNums.length; i++) {
            System.out.println(newNums[i][0] + " " + newNums[i][1]);
        }


    }

    @Test
    public void search() {

        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(ArrayListSolution.search(nums, 13));
    }

    @Test
    public void removeElement() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(ArrayListSolution.removeElement(nums, 2));
    }

    @Test
    public void sortedSquares() {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] result = ArrayListSolution.sortedSquares(nums);
        for (int i = 0; i < result.length; i++) {

            System.out.print(result[i]);
        }
    }

    @Test
    public void sortedSquares2(){
        int[] nums = {-4, -1, 0, 3, 10};
        int[] result = ArrayListSolution.sortedSquares2(nums);
        for (int i = 0; i < result.length; i++) {

            System.out.print(result[i]);
        }
    }
}