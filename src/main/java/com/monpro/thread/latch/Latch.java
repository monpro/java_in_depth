package com.monpro.thread.latch;

public class Latch {
  private int count;

  public Latch(int count) {
    this.count = count;
  }

  public synchronized void await() throws InterruptedException {
    while (count > 0) {
      wait();
    }
  }

  public synchronized void countDown() {
    while (count > 0) {
      count--;
    }
    notifyAll();
  }
}
