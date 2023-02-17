package com.kang.stackAndQuene;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack {
    // Deque 接口继承了 Queue 接口
    // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
    Deque<Integer> que1;

    public MyStack() {
        que1 = new ArrayDeque<>();
    }

    public void push(int x) {
        que1.addLast(x);
    }

    public int pop() {
        int size = que1.size();
        size--;
        // 将 que1 导入 que2 ，但留下最后一个值
        while (size-- > 0) {
            que1.addLast(que1.peekFirst());
            que1.pollFirst();
        }
        int res = que1.pollFirst();
        return res;
    }

    public int top() {
        return que1.peekLast();
    }

    public boolean empty() {
        return que1.isEmpty();
    }
}
