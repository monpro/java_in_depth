package com.monpro.thread.future;

import java.util.concurrent.Callable;

class ExecuteThread<V> extends Thread {
  private V result = null;
  private Exception exception = null;
  private boolean done = false;
  private Callable<V> task;
  private Object lock;

  ExecuteThread(Callable<V> task, Object lock) {
    this.task = task;
    this.lock = lock;
  }

  @Override
  public void run() {
    try {
      result = task.call();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      synchronized (lock) {
        done = true;
        lock.notifyAll();
      }
    }
  }

  public V getResult() {
    return result;
  }

  Exception getException() {
    return exception;
  }

  boolean isDone() {
    return done;
  }
}
