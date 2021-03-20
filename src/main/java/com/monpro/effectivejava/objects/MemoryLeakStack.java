package com.monpro.effectivejava.objects;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 1.If the stack grows and then shrinks, the popped off objects will not be garbage collected
 * because the stack will only maintain obsolete references to these objects obsolete reference
 * means the object will never be de-referenced again. Because the jvm will not know the popped off
 * elements should be free. only developers know. If you manage the memory by yourself, you are tend
 * to have the memory leak issue So we need to null out the popped out objects
 */

/**
 * 2.another potential issue is cache if we leave so many objects in cache, we might need to cleanup
 * irrelevant ones
 *
 * 3.third potential issue is listeners and callbacks we might also need to clean up these
 */
public class MemoryLeakStack {

  private static final int DEFAULT_CAPACITY = 10;
  private Object[] elements;
  private int size = 0;

  public MemoryLeakStack() {
    elements = new Object[DEFAULT_CAPACITY];
  }

  public void push(Object e) {
    checkCapacity();
    elements[size++] = e;
  }

  public Object pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    // will have memory leak
    // return elements[--size];

    // null out to free memory
    Object result = elements[--size];
    elements[size] = null;
    return result;
  }

  private void checkCapacity() {
    if (elements.length == size) {
      elements = Arrays.copyOf(elements, 2 * size + 1);
    }
  }
}
