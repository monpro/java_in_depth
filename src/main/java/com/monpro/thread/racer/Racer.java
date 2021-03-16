package com.monpro.thread.racer;

class Racer extends Thread {
  private FireFlag fireFlag;

  public Racer(FireFlag fireFlag) {
    this.fireFlag = fireFlag;
  }

  @Override
  public void run() {
    try {
      this.fireFlag.waitForFire();
      System.out.println("start run " + Thread.currentThread().getName());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
