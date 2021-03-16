package com.monpro.thread.racer;

class FireFlag {
  private boolean fired = false;

  synchronized void waitForFire() throws InterruptedException {
    while (!fired) {
      wait();
    }
  }

  synchronized void fire() {
    this.fired = true;
    notifyAll();
  }
}
