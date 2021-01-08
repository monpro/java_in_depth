package com.monpro.generics.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class MaxStack {

    /** initialize your data structure here. */
    // just use list
    Deque<Integer> deque;
    PriorityQueue<Integer> maxHeap;
    public MaxStack() {
        deque = new ArrayDeque<Integer>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void push(int x) {
        deque.addLast(x);
        maxHeap.add(x);
    }

    public int pop() {
        int element = deque.removeLast();
        maxHeap.remove(element);
        return element;
    }

    public int top() {
        return deque.getLast();
    }

    public int peekMax() {
        return maxHeap.peek();
    }

    public int popMax() {
        int element = maxHeap.poll();
        deque.removeLastOccurrence(element);
        return element;
    }

  public static void main(String[] args) {
    MaxStack maxStack = new MaxStack();
    maxStack.push(3);
    maxStack.push(11);
    maxStack.push(9);
    maxStack.push(5);

    System.out.println(maxStack.top());
    System.out.println(maxStack.pop());
    System.out.println(maxStack.peekMax());
    System.out.println(maxStack.popMax());
  }
}

