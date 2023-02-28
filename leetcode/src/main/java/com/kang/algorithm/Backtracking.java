package com.kang.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        combineSum3(k,n,0,1);

        return result;

    }

    private void combineSum3(int k, int targetSum, int sum, int startIndex) {
        if (sum > targetSum){
            return;
        }


        if (path.size() == k){
            if (targetSum == sum){
                result.add(new ArrayList<>(path));
            }
        }
        //// 减枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1 ; i++) {
            sum = sum + i;
            path.add(i);
            combineSum3(k,targetSum,sum,i+1);
            sum = sum -i;
            path.removeLast();
            
        }
    }


    /**
     * 17. Letter Combinations of a Phone Number
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
        if (num == digits.length()){
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

}
