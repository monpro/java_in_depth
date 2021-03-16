package com.monpro.generics.array;

import java.util.Iterator;

class DynamicArrayList<E> implements Iterable<E> {

  private DynamicArray<E> array;

  private DynamicArrayList(DynamicArray<E> array) {
    this.array = array;
  }

  public static void main(String[] args) {
    DynamicArray<Double> doubleArray = new DynamicArray<>();
    doubleArray.add(7.1);
    doubleArray.add(8.1);
    doubleArray.add(9.1);

    DynamicArrayList<Double> list = new DynamicArrayList<>(doubleArray);
    for (double num : list) {
      System.out.println(num);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return new DynamicArrayIterator<>(array);
  }
}
