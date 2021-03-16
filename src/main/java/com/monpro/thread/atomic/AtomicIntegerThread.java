package com.monpro.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerThread implements Runnable {

  private static final AtomicInteger atomicInteger = new AtomicInteger();
  private static volatile int count = 0;

  public static void main(String[] args) throws InterruptedException {
    AtomicIntegerThread integerThread = new AtomicIntegerThread();
    Thread thread1 = new Thread(integerThread);
    Thread thread2 = new Thread(integerThread);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    System.out.println("atomicInteger: " + atomicInteger.get());
    System.out.println("count: " + count);
  }

  private void incrementAtomic() {
    atomicInteger.getAndIncrement();
  }

  // no thread safe
  // add synchronized to enable thread safe
  private void incrementCount() {
    count += 1;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      incrementAtomic();
      incrementCount();
    }
  }
}
