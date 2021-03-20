package com.monpro.effectivejava.objects.singletonpattern;

import java.io.Serializable;

public class SingletonPattern implements Serializable {

  /**
   * singleton is simply a class that is instantiated exactly once. It typically represents either a
   * stateless object such as a function on a system component There are three ways to implement
   * singletons
   */

  /** 1.public static member to provide access to the sole instance + keeping constructor private */
  public static final SingletonPattern INSTANCE = new SingletonPattern();
  /** 2.public static factory method + private static member */
  private static final SingletonPattern PRIVATE_INSTANCE = new SingletonPattern();

  private SingletonPattern() {}

  public static SingletonPattern getInstance() {
    return PRIVATE_INSTANCE;
  }

  /** This is to make sure we only have one INSTANCE exist in JVM when serialize and deserialize */
  private Object readResolve() {
    return INSTANCE;
  }
}
