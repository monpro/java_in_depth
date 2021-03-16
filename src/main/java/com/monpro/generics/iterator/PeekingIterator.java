package com.monpro.generics.iterator;

import java.util.Iterator;

class PeekingIterator<E> implements Iterator<E> {
  private E next = null;
  private Iterator<E> it;
  private boolean end;
  // first iterator
  // next = 1, iterator[2, 3]
  // call peak return 1
  // call next() return 1(next)
  public PeekingIterator(Iterator<E> iterator) {
    // initialize any member here.
    it = iterator;
    if (it.hasNext()) {
      next = it.next();
      end = false;
    } else {
      end = true;
    }
  }

  // Returns the next element in the iteration without advancing the iterator.
  public E peek() {
    return next;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public E next() {
    E result = next;
    if (it.hasNext()) {
      next = it.next();
    } else {
      next = null;
      end = true;
    }
    return result;
  }

  @Override
  public boolean hasNext() {
    return !end;
  }
}
