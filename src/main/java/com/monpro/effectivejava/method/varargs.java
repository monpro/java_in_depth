package com.monpro.effectivejava.method;

public class varargs {
  /**
   * This function has several problems: 1.if it is invoked with no arguments, it fails at run time
   * rather than compile time 2. it is ugly because you have to include an explicit array check and
   * you need to initialize variable min
   *
   * @param args
   * @return the min value of array
   */
  static int min(int... args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Too few arguments:");
    }
    int min = args[0];
    for (int i = 1; i < args.length; i++) {
      if (args[i] < min) {
        min = args[i];
      }
    }
    return min;
  }

  /**
   * it corrects the deficiencies of the previous one
   *
   * @param firstArg
   * @param remainingArgs
   * @return the min value of passed args
   */
  static int minTwoParameters(int firstArg, int... remainingArgs) {
    int min = firstArg;
    for (int arg : remainingArgs) {
      if (arg < min) {
        min = arg;
      }
    }
    return min;
  }

  /**
   * you need to know every invocation of a varargs method causes an array allocation and
   * initialization so you could declare multiple overloadings of a method + a single varargs method
   * now you need to pay the cost of array creation on in small percent of all invocations
   */
  static void overloadMethod() {}

  static void overloadMethod(int a1) {}

  static void overloadMethod(int a1, int a2) {}

  static void overloadMethod(int a1, int a2, int a3) {}

  static void overloadMethod(int a1, int a2, int a3, int a4) {}

  static void overloadMethod(int a1, int a2, int a3, int a4, int... rest) {}

  public static void main(String[] args) {}
}
