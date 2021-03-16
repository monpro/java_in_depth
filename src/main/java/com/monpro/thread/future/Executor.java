package com.monpro.thread.future;

import java.util.concurrent.Callable;

class Executor<V> {
  public <V> Future<V> execute(final Callable<V> task) {
    final Object lock = new Object();
    final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
    thread.start();

    Future<V> future =
        new Future<V>() {
          @Override
          public V get() throws Exception {
            synchronized (lock) {
              while (!thread.isDone()) {
                lock.wait();
              }
            }
            if (thread.getException() != null) {
              throw thread.getException();
            }
            return thread.getResult();
          }
        };

    return future;
  }
}
