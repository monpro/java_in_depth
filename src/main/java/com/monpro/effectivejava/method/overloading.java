package com.monpro.effectivejava.method;

import java.math.BigInteger;
import java.util.*;

public class overloading {

  static class Wine {
    String name() {
      return "wine";
    }
  }

  static class SparklingWine extends Wine {
    @Override
    String name() {
      return "sparkling wine";
    }
  }

  static class Champagne extends SparklingWine {
    @Override
    String name() {
      return "champagne";
    }
  }

  public static String classify(Set<?> s) {
    return "Set";
  }

  public static String classify(List<?> lst) {
    return "List";
  }

  public static String classify(Collection<?> c) {
    return "Unknown Collection";
  }

  public static String classifyWithoutOverload(Collection<?> c) {
    return c instanceof Set ? "Set" : c instanceof List ? "List" : "Unknown Collection";
  }

  public static void main(String[] args) {
    Collection<?>[] collections = {
      new HashSet<String>(), new ArrayList<BigInteger>(), new HashMap<String, String>().values()
    };
    // the collection<?> is determined at compile time
    for (Collection<?> c : collections) System.out.println(classify(c));
    // c instance of Set is determined at run time
    for (Collection<?> c : collections) System.out.println(classifyWithoutOverload(c));

    List<Wine> wineList =
        new ArrayList<>(Arrays.asList(new Wine(), new SparklingWine(), new Champagne()));

    // wine.name() is determined at run time
    for (Wine wine : wineList) System.out.println(wine.name());
  }
}
