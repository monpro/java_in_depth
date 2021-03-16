package com.monpro.generics.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/** * always make sure left.size() >= right.size() for the convenience of popMiddle operation */
class FrontMiddleBackQueue {
  private Deque<Integer> left;
  private Deque<Integer> right;

  FrontMiddleBackQueue() {
    left = new ArrayDeque<>();
    right = new ArrayDeque<>();
  }

  void pushFront(int val) {
    left.offerFirst(val);
    balance();
  }

  void pushMiddle(int val) {
    if (left.size() > right.size()) {
      right.offerFirst(left.pollLast());
    }
    left.offerLast(val);
  }

  void pushBack(int val) {
    right.offerLast(val);
    balance();
  }

  public int popFront() {
    int val = left.isEmpty() ? -1 : left.pollFirst();
    balance();
    return val;
  }

  int popMiddle() {
    int val = left.isEmpty() ? -1 : left.pollLast();
    balance();
    return val;
  }

  public int popBack() {
    int val = -1;
    if (!right.isEmpty()) {
      val = right.pollLast();
    } else if (!left.isEmpty()) {
      val = left.pollLast();
    }
    balance();
    return val;
  }

  private void balance() {
    if (left.size() > right.size() + 1) {
      right.offerFirst(left.pollLast());
    }
    if (left.size() < right.size()) {
      left.offerLast(right.pollFirst());
    }
  }
}
