package com.monpro.effectivejava.objects;

public class NonInstantiability {

  /**
   * This is for a class that is just a grouping of static methods and static fields the explict
   * constructor is private, inaccessible outside the class it guarantees the class will never be
   * instantiated under any circumstances it also prevent the class being subclassed. because a
   * subclass must invoke a superclass constructor explicitly or implicitly
   */
  private NonInstantiability() {
    throw new AssertionError();
  }
}
