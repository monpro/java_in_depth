package com.monpro.generics.array;

class PhoneBook {
  private int[] next;
  private int cur;

  public PhoneBook(int maxNumbers) {
    next = new int[maxNumbers];
    cur = 0;
    for (int i = 0; i < maxNumbers; i++) {
      next[i] = (i + 1) % maxNumbers;
    }
  }

  /**
   * Provide a number which is not assigned to anyone.
   *
   * @return - Return an available number. Return -1 if none is available.
   */
  public int get() {
    if (next[cur] == -1) {
      return -1;
    }
    int result = cur;
    cur = next[cur];
    // originalCur
    next[result] = -1;
    return result;
  }

  /** Check if a number is available or not. */
  public boolean check(int number) {
    return next[number] != -1;
  }

  /** Recycle or release a number. */
  public void release(int number) {
    if (next[number] != -1) {
      return;
    }
    next[number] = cur;
    cur = number;
  }
}
