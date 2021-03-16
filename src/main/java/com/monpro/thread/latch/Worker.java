package com.monpro.thread.latch;

class Worker extends Thread {
  private Latch latch;

  Worker(Latch latch) {
    this.latch = latch;
  }

  @Override
  public void run() {
    try {
      // simulation worker running
      Thread.sleep((int) (Math.random() * 1000));
      System.out.println("worker: " + Thread.currentThread().getName());
      this.latch.countDown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
