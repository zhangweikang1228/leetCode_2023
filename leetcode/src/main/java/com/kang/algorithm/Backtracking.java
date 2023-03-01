package com.kang.algorithm;

import com.sun.xml.internal.txw2.output.DumpSerializer;

import java.util.*;

public class Backtracking {


    /**
     * 77. Combinations
     *
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     *
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    private void combineHelper(int n, int k, int startIndex) {
        // 中止条件
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 循环处理
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineHelper(n, k, i + 1);
            path.removeLast();
        }

    }


    /**
     * 216. Combination Sum III
     * k numbers that sum up to n
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        combineSum3(k, n, 0, 1);

        return result;

    }

    private void combineSum3(int k, int targetSum, int sum, int startIndex) {
        if (sum > targetSum) {
            return;
        }


        if (path.size() == k) {
            if (targetSum == sum) {
                result.add(new ArrayList<>(path));
            }
        }
        //// 减枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            sum = sum + i;
            path.add(i);
            combineSum3(k, targetSum, sum, i + 1);
            sum = sum - i;
            path.removeLast();

        }
    }


    /**
     * 17. Letter Combinations of a Phone Number
     *
     * @param digits
     * @return
     */

    //设置全局列表存储最后的结果
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代处理
        backTrackingLetter(digits, numString, 0);
        return list;

    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    private void backTrackingLetter(String digits, String[] numString, int num) {
        // 符合返回条件
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //c
            backTrackingLetter(digits, numString, num + 1);
            //剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }

    }


    /**
     * 39. Combination Sum
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先进行排序
        backtracking(res, new ArrayList<>(), candidates, target, 0, 0);
        return res;

    }

    private void backtracking(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int startIndex) {
        // 找到了数字和为 target 的组合
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            // 如果 sum + candidates[i] > target 就终止遍历
            if (sum + candidates[i] > target) break;
            path.add(candidates[i]);
            backtracking(res, path, candidates, target, sum + candidates[i], i);
            path.remove(path.size() - 1); // 回溯，移除路径 path 最后一个元素
        }
    }


    /**
     * 40. Combination Sum II
     *
     * @param candidates
     * @param target
     * @return
     */
    // 标记一个used数组 用来标记哪些元素已经用过了
    boolean[] used;
    int sumSum2 = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        used = new boolean[candidates.length];
        Arrays.fill(used, false);
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backTrackingSum2(candidates, target, 0);
        return result;
    }

    private void backTrackingSum2(int[] candidates, int target, int startIndex) {

        if (sumSum2 == target) {
            result.add(new ArrayList<>(path));
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (sumSum2 + candidates[i] > target) {
                break;
            }
            // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            sumSum2 = sumSum2 + candidates[i];
            path.add(candidates[i]);
            // 每个节点仅能选择一次，所以从下一位开始
            backTrackingSum2(candidates, target, i + 1);
            used[i] = false;
            sumSum2 = sumSum2 - candidates[i];
            path.removeLast();

        }


    }


    /**
     * 131. Palindrome Partitioning
     *
     * @param s
     * @return
     */

    List<List<String>> lists = new ArrayList<>();
    Deque<String> deque = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return lists;

    }

    private void backTracking(String s, int startIndex) {
        // 如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= s.length()) {
            lists.add(new ArrayList(deque));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            // 如果是回文子串，则记录
            if (isPalindrome(s, startIndex, i)) {
                String str = s.substring(startIndex, i + 1);
                deque.addLast(str);
            } else {
                continue;
            }
            //起始位置后移，保证不重复
            backTracking(s, i + 1);
            deque.removeLast();
        }
    }

    //判断是否是回文串
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 93. Restore IP Addresses
     *
     * @param s
     * @return
     */
    List<String> resultString = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return resultString; // 算是剪枝了
        Date date = new Date();

        backTracking(s, 0, 0);

        return resultString;
    }

    // startIndex: 搜索的起始位置， pointNum:添加逗点的数量
    private void backTracking(String s, int startIndex, int pointSum) {
        if (pointSum == 3) { // 逗点数量为3时，分隔结束
            // 判断第四段⼦字符串是否合法，如果合法就放进result中
            if (isValidIp(s, startIndex, s.length() - 1)) {
                resultString.add(s);
            }

            return;
        }

        // 单层搜索逻辑
        for (int i = startIndex; i < s.length(); i++) {
            // 子串进行合法性校验
            if (isValidIp(s, startIndex, i)) {
                // 在str的后⾯插⼊⼀个逗点
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointSum++;
                // 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                backTracking(s, i + 2, pointSum);
                // 回溯
                pointSum--;
                s = s.substring(0, i + 1) + s.substring(i + 2);// 回溯删掉逗点

            } else {
                break;
            }

        }

    }

    private boolean isValidIp(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 0开头的数字不合法
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果⼤于255了不合法
                return false;
            }
        }
        return true;

    }


    /**
     * 78. Subsets
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {

        backTrackingSubsets(nums, 0);
        return result;

    }

    private void backTrackingSubsets(int[] nums, int startIndex) {
        // 遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合
        result.add(new ArrayList<>(path));
        // 终止条件可 不加
        if (startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            backTrackingSubsets(nums, i + 1);
            path.removeLast();
        }

    }

}

