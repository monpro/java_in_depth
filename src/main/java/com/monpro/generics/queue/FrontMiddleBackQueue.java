package com.monpro.generics.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * always make sure left.size() >= right.size()
 * for the convenience of popMiddle operation
 */
public class FrontMiddleBackQueue {
    Deque<Integer> left;
    Deque<Integer> right;
    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        left.offerFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if(left.size() > right.size()) {
            right.offerFirst(left.pollLast());
        }
        left.offerLast(val);
    }

    public void pushBack(int val) {
        right.offerLast(val);
        balance();

    }

    public int popFront() {
        int val = left.isEmpty() ? -1 : left.pollFirst();
        balance();
        return val;
    }

    public int popMiddle() {
        int val = left.isEmpty() ? -1: left.pollLast();
        balance();
        return val;
    }

    public int popBack() {
        int val = -1;
        if(!right.isEmpty()) {
            val = right.pollLast();
        } else if(!left.isEmpty()) {
            val = left.pollLast();
        }
        balance();
        return val;
    }

    private void balance() {
        if(left.size() > right.size() + 1) {
            right.offerFirst(left.pollLast());
        }
        if(left.size() < right.size()) {
            left.offerLast(right.pollFirst());
        }
    }
}