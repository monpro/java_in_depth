package com.monpro.generics.array;

class Compare {
  private static <T extends Comparable<T>> T max(T[] arr) {
    T max = arr[0];
    for (T ele : arr) {
      if (ele.compareTo(max) > 0) {
        max = ele;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    // here only Integer not int, because the T must extends the Comparable Interface
    Integer[] intArr =
        new Integer[] {
          1, 3, 2, -1,
        };
    Double[] doubleArr = new Double[] {1.0, -1.3, 1.2};
    System.out.println(max(intArr));
    System.out.println(max(doubleArr));
  }
}
