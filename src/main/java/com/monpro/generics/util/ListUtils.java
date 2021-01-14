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

    public static ListNode swapNodes(ListNode head, int k) {
        int length = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode node = dummy;
        while(node.next != null) {
            node = node.next;
            length += 1;
        }

        // reset
        ListNode firstKNode = null, lastKNode = null;
        ListNode nextFirstKNode = null, nextLastKNode = null;
        node = dummy;
        ListNode prevFirstKNode = null, prevLastKNode = null;
        for(int i = 0; i < k; i++) {
            prevFirstKNode = node;
            node = node.next;
        }
        firstKNode = node;
        nextFirstKNode = node.next;

        node = dummy;
        for(int i = 0; i < length - k + 1; i++) {
            prevLastKNode = node;
            node = node.next;
        }
        lastKNode = node;
        nextLastKNode = node.next;
        // System.out.println(prevFirstKNode.val + " " + firstKNode.val + " " + nextFirstKNode.val);
        // System.out.println(prevLastKNode.val + " " + lastKNode.val + " " + nextLastKNode.val);
        if(firstKNode.next == lastKNode) {
            prevFirstKNode.next = lastKNode;
            lastKNode.next = firstKNode;
            firstKNode.next = nextLastKNode;
        } else if(lastKNode.next == firstKNode) {
            prevLastKNode.next = firstKNode;
            firstKNode.next = lastKNode;
            lastKNode.next = nextFirstKNode;
        }else {
            prevFirstKNode.next = lastKNode;
            lastKNode.next = nextFirstKNode;
            prevLastKNode.next = firstKNode;
            firstKNode.next = nextLastKNode;
        }
        return dummy.next;
    }
}
