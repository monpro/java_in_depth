package com.monpro.thread.latch;

class Main {
  public static void main(String[] args) throws InterruptedException {
    int num = 100;
    Latch latch = new Latch(num);
    Worker[] workers = new Worker[num];
    for (int i = 0; i < num; i++) {
      workers[i] = new Worker(latch);
      workers[i].start();
    }

    latch.await();
    System.out.println("workers executed finish");
  }
}
