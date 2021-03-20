package com.monpro.effectivejava.objects.singletonpattern;

/** 3. use enum is a more concise way */
public enum EnumSingleton {
  INSTANCE;

  int value;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
