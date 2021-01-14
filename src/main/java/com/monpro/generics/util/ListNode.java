package com.monpro.generics.util;

public class ListNode {
    int val;
    ListNode next;

    private ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
