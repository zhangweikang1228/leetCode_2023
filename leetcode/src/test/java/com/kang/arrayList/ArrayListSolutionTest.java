package com.kang.arrayList;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;

public class ArrayListSolutionTest {

    @Test
    public void testMerge() {
        int[][] nums = {{1,3},{2,6},{8,10},{15,18},{10,20}};
        int[][] newNums = ArrayListSolution.merge(nums);
        for (int i = 0; i < newNums.length; i++) {
            System.out.println(newNums[i][0] + " " + newNums[i][1]);
        }


    }
}