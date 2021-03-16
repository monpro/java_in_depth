package com.monpro.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class UnFairReadLock {

  // unfair readwrite lock
  private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

  private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
  private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

  private static void read() {
    System.out.println(Thread.currentThread().getName() + " try to acquire read lock");
    readLock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " acquired read lock");
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(Thread.currentThread().getName() + " released read lock");
      readLock.unlock();
    }
  }

  private static void write() {
    System.out.println(Thread.currentThread().getName() + " try to acquire write lock");
    writeLock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " acquired write lock");
      Thread.sleep(40);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(Thread.currentThread().getName() + " released write lock");
      writeLock.unlock();
    }
  }

  public static void main(String[] args) {
    new Thread(UnFairReadLock::write, "Thread 0").start();
    new Thread(UnFairReadLock::read, "Thread 1").start();
    new Thread(UnFairReadLock::read, "Thread 2").start();
    new Thread(UnFairReadLock::write, "Thread 3").start();
    new Thread(UnFairReadLock::read, "Thread 4").start();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                Thread[] thread = new Thread[1000];
                for (int i = 0; i < thread.length; i++) {
                  thread[i] = new Thread(UnFairReadLock::read, "child thread " + i);
                }
                for (int i = 0; i < thread.length; i++) {
                  thread[i].start();
                }
              }
            })
        .start();
  }
}
