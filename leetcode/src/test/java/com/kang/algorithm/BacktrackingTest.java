package com.kang.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class BacktrackingTest {

    Backtracking backtracking = new Backtracking();

    @Test
    public void restoreIpAddresses() {

        String s = "25525511135";
        backtracking.restoreIpAddresses(s);

    }

    @Test
    public void findSubsequences() {

        int[] n = {4,6,7,7};
        backtracking.findSubsequences(n);
    }
}