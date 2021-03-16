package com.monpro.generics.util;

import java.util.Objects;

class ListUtils {
  private ListUtils() {}

  static ListNode MergeListBetweenNodes(ListNode list1, int a, int b, ListNode list2) {
    ListNode dummy = new ListNode(-1);
    dummy.next = list1;
    ListNode head = dummy;
    for (int i = 0; i < a; i++) {
      head = head.next;
    }

    ListNode nextNode = head;
    for (int i = 0; i < b - a + 2; i++) {
      nextNode = nextNode.next;
    }

    ListNode newHead = list2;

    head.next = newHead;

    while (newHead.next != null) {
      newHead = newHead.next;
    }

    newHead.next = nextNode;
    return dummy.next;
  }

  static ListNode swapNodes(ListNode head, int k) {
    int length = 0;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode node = dummy;
    while (node.next != null) {
      node = node.next;
      length += 1;
    }

    // reset
    ListNode firstKNode, lastKNode;
    ListNode nextFirstKNode, nextLastKNode;
    node = dummy;
    ListNode prevFirstKNode = null, prevLastKNode = null;
    for (int i = 0; i < k; i++) {
      prevFirstKNode = node;
      node = Objects.requireNonNull(node).next;
    }
    firstKNode = node;
    nextFirstKNode = Objects.requireNonNull(node).next;

    node = dummy;
    for (int i = 0; i < length - k + 1; i++) {
      prevLastKNode = node;
      node = Objects.requireNonNull(node).next;
    }
    lastKNode = node;
    nextLastKNode = Objects.requireNonNull(node).next;
    // System.out.println(prevFirstKNode.val + " " + firstKNode.val + " " + nextFirstKNode.val);
    // System.out.println(prevLastKNode.val + " " + lastKNode.val + " " + nextLastKNode.val);
    if (Objects.requireNonNull(firstKNode).next == lastKNode) {
      Objects.requireNonNull(prevFirstKNode).next = lastKNode;
      Objects.requireNonNull(lastKNode).next = firstKNode;
      firstKNode.next = nextLastKNode;
    } else if (lastKNode.next == firstKNode) {
      Objects.requireNonNull(prevLastKNode).next = firstKNode;
      firstKNode.next = lastKNode;
      lastKNode.next = nextFirstKNode;
    } else {
      Objects.requireNonNull(prevFirstKNode).next = lastKNode;
      lastKNode.next = nextFirstKNode;
      Objects.requireNonNull(prevLastKNode).next = firstKNode;
      firstKNode.next = nextLastKNode;
    }
    return dummy.next;
  }
}
