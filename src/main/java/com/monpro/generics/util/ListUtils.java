package com.monpro.generics.util;

public class ListUtils {
    private ListUtils() {

    }

    public static ListNode MergeListBetweenNodes(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        dummy.next = list1;
        ListNode head = dummy;
        for(int i = 0; i < a; i++) {
            head = head.next;
        }

        ListNode nextNode = head;
        for(int i = 0; i < b - a + 2; i++) {
            nextNode = nextNode.next;
        }


        ListNode newHead = list2;

        head.next = newHead;

        while(newHead.next != null) {
            newHead = newHead.next;
        }

        newHead.next = nextNode;
        return dummy.next;
    }
}
