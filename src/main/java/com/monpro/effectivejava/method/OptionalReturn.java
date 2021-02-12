package com.monpro.effectivejava.method;

import java.util.*;

public class OptionalReturn {
  /**
   * this method may not have return value throw an exception is expensive and it should be reserved
   * for exceptional conditions
   *
   * @param c
   * @param <E>
   * @return the max value in the collection containing comparable element
   * @throws IllegalArgumentException if the collection is empty
   */
  public static <E extends Comparable<E>> E max(Collection<E> c) {
    if (c.isEmpty()) throw new IllegalArgumentException("Empty collection");
    E result = null;
    for (E e : c) if (result == null || e.compareTo(result) > 0) result = Objects.requireNonNull(e);

    return result;
  }

  /**
   * optional allows the method to return an empty result to indicate that it couldn’t return a
   * valid result
   *
   * @param c
   * @param <E>
   * @return the max value in the collection containing comparable element
   */
  public static <E extends Comparable<E>> Optional<E> maxWithOptional(Collection<E> c) {

    if (c.isEmpty()) return Optional.empty();
    E result = null;
    for (E e : c) {
      if (result == null || e.compareTo(result) > 0) {
        result = Objects.requireNonNull(e);
      }
    }
    return Optional.of(result);
  }

  /**
   * you should declare a method to return Optional<T> if it might not be able to return a result
   * and clients will have to perform special processing if no result is returned. Returning an
   * optional that contains a boxed primitive type is prohibitively expensive compared to returning
   * a primitive type because the optional has two levels of boxing instead of zero. you should
   * never return an optional of a boxed primitive type,
   */
  public static void main(String[] args) {
    List<Integer> emptyList = new ArrayList<>();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    int maxInteger = maxWithOptional(emptyList).orElse(-1);
    System.out.println(maxInteger);
    System.out.println(maxWithOptional(list).map(value -> String.valueOf(value)).orElse("-1"));
  }
}
