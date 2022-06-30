package com.monpro.generics.design;

import java.util.Arrays;

public class ATM {
  private final long[] banknotes = {20, 50, 100, 200, 500};
  private final long[] deposits;

  public ATM() {
    deposits = new long[5];
  }

  public void deposit(int[] banknotesCount) {
    for (int i = 0; i < 5; i++) {
      deposits[i] += banknotesCount[i];
    }
  }

  public int[] withdraw(int amount) {
    // we iterate banknotes backwards
    long[] result = new long[5];
    int index = 4;
    while (index >= 0 && amount > 0) {
      long num = Math.min(amount / banknotes[index], deposits[index]);
      result[index] = num;
      amount -= num * banknotes[index];
      index -= 1;
    }
    if (amount != 0) {
      return new int[] {-1};
    } else {
      for (int i = 0; i < 5; i++) {
        deposits[i] -= result[i];
      }
      return Arrays.stream(result).mapToInt(i -> (int) i).toArray();
    }
  }
}
