package com.monpro.effectivejava.classes;

import java.util.List;

public class MinimizeAccess {

  /**
   * when you design your api, you should make classes or member as inaccessible as possible 1.
   * considering make a top-level class private static or sole class 2. sub class method cannot have
   * more restrictive access level than one in super class this will make sure any place subclass
   * could be used anywhere the superclass is used 3. Instance fields shouldn't be public except
   * public static final fields public fields are always not thread-safe
   */

  /**
   * for public static final fields, if it is mutable, may have security issues
   *
   * <p>the client could modify the member values because it is mutable
   */
  public static final String[] VALUES = {};
  /** or make the VALUES private and we return a List */
  public static final List<String> IMMUTABLE_VALUES_LIST = List.of(VALUES);
  /** so we need to return immutable ones to client */
  private static final String[] IMMUTABLE_VALUES = {};

  public static final String[] values() {
    return IMMUTABLE_VALUES.clone();
  }
}
