package com.monpro.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

class AtomicArrayThread {

  public static void main(String[] args) {
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
    Incrementer incrementer = new Incrementer(atomicIntegerArray);
    Decrementer decrementer = new Decrementer(atomicIntegerArray);
    Thread[] incrementThreads = new Thread[100];
    Thread[] decrementerThreads = new Thread[100];
    for (int i = 0; i < 100; i++) {
      incrementThreads[i] = new Thread(incrementer);
      decrementerThreads[i] = new Thread(decrementer);
      incrementThreads[i].start();
      decrementerThreads[i].start();
    }

    for (int i = 0; i < 100; i++) {
      try {
        incrementThreads[i].join();
        decrementerThreads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      System.out.println(atomicIntegerArray.get(i));
    }
  }
}

class Decrementer implements Runnable {

  private AtomicIntegerArray atomicIntegerArray;

  public Decrementer(AtomicIntegerArray array) {
    this.atomicIntegerArray = array;
  }

  @Override
  public void run() {
    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      atomicIntegerArray.getAndDecrement(i);
    }
  }
}

class Incrementer implements Runnable {

  private AtomicIntegerArray atomicIntegerArray;

  public Incrementer(AtomicIntegerArray array) {
    this.atomicIntegerArray = array;
  }

  @Override
  public void run() {
    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      atomicIntegerArray.getAndIncrement(i);
    }
  }
}
