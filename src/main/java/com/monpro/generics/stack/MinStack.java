package com.monpro.generics.stack;

import java.util.Stack;

class MinStack {

  /** initialize your data structure here. */
  private Stack<Integer> stack;
  private Stack<Integer> minStack;

  public MinStack() {
    stack = new Stack<Integer>();
    minStack = new Stack<Integer>();
  }

  public void push(int x) {
    stack.add(x);
    if (minStack.isEmpty()) {
      minStack.add(x);
    } else if (minStack.peek() >= x) {
      minStack.add(x);
    }
  }

  public void pop() {
    int element = stack.pop();
    if (element == minStack.peek()) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }
}
