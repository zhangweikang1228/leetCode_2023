package com.kang.linkList;

import org.junit.Test;

public class TestLinkListSolution {

    @Test
    public void testAddTwoNumber(){
        ListNode listNode1_0 = new ListNode(2);
        ListNode listNode1_1 = new ListNode(4);
        ListNode listNode1_2 = new ListNode(3);
        ListNode listNode2_0 = new ListNode(5);
        ListNode listNode2_1 = new ListNode(6);
        ListNode listNode2_2 = new ListNode(4);

        listNode1_1.next = listNode1_2;
        listNode1_0.next = listNode1_1;
        listNode2_1.next = listNode2_2;
        listNode2_0.next = listNode2_1;

        LinkListSolution.addTwoNumbers(listNode1_0,listNode2_0);
        LinkListSolution.addTwoNumbers(listNode1_1,listNode2_1);






    }
}
