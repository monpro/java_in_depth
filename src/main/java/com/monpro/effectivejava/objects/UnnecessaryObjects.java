package com.monpro.effectivejava.objects;

import java.util.regex.Pattern;

public class UnnecessaryObjects {

  /**
   * 1.An object can always be reused if it is immutable
   *
   * <p>// don't do this String s = new String("abc"); because it will create a String instance
   * every time it's executed
   *
   * <p>// Do This String s = "abc"; it create a single String instance rather than creating a new
   * one further more, this object will reused by any other code running in same JVM
   */

  /** some object creation is expensive you may need to cache it to make it immutable */

  private static final Pattern digitPattern = Pattern.compile("\\d+");

  /** harmful to performance every time it will create a pattern instance */
  static boolean isDigits(String s) {
    return s.matches("\\d+");
  }

  /** huge improvement on performance it will reuse the same instance and have more readability */
  static boolean isDigitsCache(String s) {
    return digitPattern.matcher(s).matches();
  }

  /** you also need to consider the unnecessary auto boxing */

  /**
   * it's pretty slow cause every time when i is added to sum it will create an unnecessary object
   * so the correct way is to use long sum = 0l; prefer primitive type over boxed primitives
   */
  private static long getSum() {
    Long sum = 0l;
    for (long i = 0; i <= Integer.MAX_VALUE; i++) {
      sum += i;
    }
    return sum;
  }
}
