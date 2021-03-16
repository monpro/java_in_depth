package com.monpro.generics.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListUtilsTest {
  @Test
  void TestMergeListBetweenNodes() {
    ListNode head1 = new ListNode(1);
    head1.next = new ListNode(2);
    head1.next.next = new ListNode(3);
    head1.next.next.next = new ListNode(4);

    ListNode head2 = new ListNode(7);
    head2.next = new ListNode(8);
    head2.next.next = new ListNode(9);

    ListUtils.MergeListBetweenNodes(head1, 1, 2, head2);
    assertEquals(head1.next.val, 7);
    assertEquals(head1.next.next.val, 8);
  }

  @Test
  void TestSwapNodes() {
    ListNode head1 = new ListNode(1);
    head1.next = new ListNode(2);
    head1.next.next = new ListNode(3);
    head1.next.next.next = new ListNode(4);
    head1.next.next.next.next = new ListNode(5);

    ListUtils.swapNodes(head1, 2);
    assertEquals(head1.next.val, 4);
    assertEquals(head1.next.next.val, 3);
    assertEquals(head1.next.next.next.val, 2);
  }
}
