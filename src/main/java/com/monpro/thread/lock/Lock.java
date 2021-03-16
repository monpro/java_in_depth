package com.monpro.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

class Lock {
  // 0 unlock, 1 locked
  private AtomicInteger status = new AtomicInteger(0);

  public void lock() {
    while (!status.compareAndSet(0, 1)) {
      Thread.yield();
    }
  }

  public void unlock() {
    status.compareAndSet(1, 0);
  }
}
