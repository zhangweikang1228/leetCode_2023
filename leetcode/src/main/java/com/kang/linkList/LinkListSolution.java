package com.kang.linkList;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class LinkListSolution {

    /**
     * 2.Add Two Numbers
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode current = result;
        int carry = 0;

        while (l1 != null || l2 != null || carry == 1) {

            int sum = 0;
            if (l1 != null) {
                sum = sum + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum = sum + l2.val;
                l2 = l2.next;
            }
            sum = sum + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            current.next = node;
            //current.val = ;
            current = current.next;

        }

        return result.next;
    }


    /**
     * 203. Remove Linked List Elements
     * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
     * and return the new head.
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curr = head, prev = fakeHead;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }
        return fakeHead.next;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        // ?????????null???????????????
        if (head == null) {
            return head;
        }
        // ???????????????head.val != val
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    /**
     * 206. Reverse Linked List
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;// ?????????????????????
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    /**
     * ????????????206.????????????
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;// ????????????????????????
        cur.next = prev;// ??????
        // ??????prev???cur??????
        // prev = cur;
        // cur = temp;
        return reverse(cur, temp);
    }


    /**
     * 24. Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.
     * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {

        // base case ????????????
        if (head == null || head.next == null) return head;
        // ????????????????????????????????????
        ListNode next = head.next;
        // ????????????
        ListNode newNode = swapPairs(next.next);
        // ??????????????????
        next.next = head;
        head.next = newNode;

        return next;
    }

    public static ListNode swapPairs2(ListNode head) {
        ListNode dumyhead = new ListNode(-1); // ???????????????????????????
        dumyhead.next = head; // ????????????????????????head????????????????????????????????????
        ListNode cur = dumyhead;
        ListNode temp; // ????????????????????????????????????????????????
        ListNode firstnode; // ?????????????????????????????????????????????????????????
        ListNode secondnode; // ?????????????????????????????????????????????????????????
        while (cur.next != null && cur.next.next != null) {
            temp = cur.next.next.next;
            firstnode = cur.next;
            secondnode = cur.next.next;
            cur.next = secondnode;       // ?????????
            secondnode.next = firstnode; // ?????????
            firstnode.next = temp;      // ?????????
            cur = firstnode; // cur??????????????????????????????
        }
        return dumyhead.next;
    }


    /**
     * 19. Remove Nth Node From End of List
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        //???????????????????????? n ???????????????
        for (int i = 0; i < n; i++) {
            fastIndex = fastIndex.next;
        }

        while (fastIndex.next != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        //?????? slowIndex ???????????????????????????????????????????????????
        //????????????????????????????????????????????? 3 ??????????????????????????????
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next;
    }

    /**
     * 142. Linked List Cycle II
     * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * <p>
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
     * <p>
     * Do not modify the linked list.
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// ??????
                ListNode index1 = fast;
                ListNode index2 = head;
                // ???????????????????????????????????????????????????????????????????????????????????????????????????
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }

    /**
     * 160. Intersection of Two Linked Lists
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;
        while (curA != null) { // ?????????A?????????
            lenA++;
            curA = curA.next;
        }
        while (curB != null) { // ?????????B?????????
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        // ???curA????????????????????????lenA????????????
        if (lenB > lenA) {
            //1. swap (lenA, lenB);
            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
            //2. swap (curA, curB);
            ListNode tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }
        // ????????????
        int gap = lenA - lenB;
        // ???curA???curB??????????????????????????????????????????
        while (gap-- > 0) {
            curA = curA.next;
        }
        // ??????curA ??? curB??????????????????????????????
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;

    }


}
