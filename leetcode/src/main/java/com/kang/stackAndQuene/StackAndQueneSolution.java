package com.kang.stackAndQuene;

import java.util.*;

public class StackAndQueneSolution {


    /**
     * 20. Valid Parentheses
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if ('(' == ch) {
                deque.push(')');
            } else if (ch == '{') {
                deque.push('}');
            } else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            } else {//如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }

        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }


    /**
     * 1047. Remove All Adjacent Duplicates In String
     * You are given a string s consisting of lowercase English letters.
     * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
     * We repeatedly make duplicate removals on s until we no longer can.
     * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        // 用栈的思想去解决这个问题
        // 也可以用 StringBuilder 来修改字符串，速度更快
        // StringBuilder res = new StringBuilder();
        StringBuffer sb = new StringBuffer();
        // top为 sb 的长度
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && sb.charAt(top) == c) {
                sb.deleteCharAt(top);
                top--;
            } else {  // 否则，将该字符 入栈，同时top++
                sb.append(c);
                top++;
            }
        }
        return sb.toString();
    }

    /**
     * 150. Evaluate Reverse Polish Notation
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList();
        for (String s : tokens) {
            if ("+".equals(s)) {
                stack.push(stack.pop() + stack.pop());      // 注意 - 和/ 需要特殊处理
            } else if ("-".equals(s)) {
                stack.push(-stack.pop() + stack.pop());
            } else if ("*".equals(s)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(s)) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2 / temp1);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    /**
     * 239. Sliding Window Maximum
     * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     * <p>
     * Return the max sliding window.
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        return nums;
    }


    /**
     * 347. Top K Frequent Elements
     * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 基于大顶堆实现
        // hashMap key-为数组元素的值 value-元素出现的次数
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int n : nums) {
            hashMap.put(n, hashMap.getOrDefault(n, 0) + 1);
        }
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        };
        // 在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        // 出现次数按从队头到队尾的顺序是从大到小排,出现次数最多的在队头(相当于大顶堆)
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(comparator);
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            // 大顶堆需要对所有元素进行排序
            priorityQueue.add(new int[]{entry.getKey(), entry.getValue()});
        }
        // 定一个 k 大小的数组
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            // 依次从队头弹出k个,就是出现频率前k高的元素
            ans[i] = priorityQueue.poll()[0];
        }

        return ans;
    }

    //解法2：基于小顶堆实现
    public int[] topKFrequent2(int[] nums, int k) {
        // key为数组元素值,val为对应出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        // 出现次数按从队头到队尾的顺序是从小到大排,出现次数最低的在队头(相当于小顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // 小顶堆只需要维持k个元素有序
            // 小顶堆元素个数小于k个时直接加
            if (pq.size() < k) {
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            } else {
                // 当前元素出现次数大于小顶堆的根结点(这k个元素中出现次数最少的那个)
                if (entry.getValue() > pq.peek()[1]) {
                    // 弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    pq.poll();
                    pq.add(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }
        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {// 依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}
