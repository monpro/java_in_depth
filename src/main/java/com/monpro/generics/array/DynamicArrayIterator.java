package com.monpro.generics.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayIterator<E> implements Iterator<E> {

  private DynamicArray<E> array;
  private int cursor = 0;
  private int lastCursor = -1;

  public DynamicArrayIterator(DynamicArray<E> array) {
    this.array = array;
  }

  public static void main(String[] args) {
    DynamicArray<Double> doubleArray = new DynamicArray<>();
    doubleArray.add(7.1);
    doubleArray.add(8.1);
    doubleArray.add(9.1);
    DynamicArrayIterator<Double> iterator = new DynamicArrayIterator<>(doubleArray);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  @Override
  public boolean hasNext() {
    return cursor != array.size();
  }

  @Override
  public E next() {
    int i = cursor;
    if (i >= array.size()) {
      throw new NoSuchElementException();
    }
    cursor = i + 1;
    lastCursor = i;
    return array.get(i);
  }

  @Override
  public void remove() {
    if (lastCursor < 0) {
      throw new IllegalStateException();
    }
    array.remove(lastCursor);
    cursor = lastCursor;
    lastCursor = -1;
  }
}
