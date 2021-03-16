package com.monpro.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

class Main {
  private static AtomicInteger counter = new AtomicInteger(0);

  public static void main(String[] args) throws InterruptedException {
    int num = 10000;
    Thread[] threads = new Thread[num];
    for (int i = 0; i < num; i++) {
      threads[i] = new Counter();
      threads[i].start();
    }
    for (int i = 0; i < num; i++) {
      threads[i].join();
    }

    System.out.println(counter.get());
  }

  static class Counter extends Thread {
    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        counter.incrementAndGet();
      }
    }
  }
}
