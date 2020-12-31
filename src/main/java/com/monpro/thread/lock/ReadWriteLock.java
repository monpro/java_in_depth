package com.monpro.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "get read lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "release read lock");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "get write lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "release write lock");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> write(), "Thread 0").start();
        new Thread(() -> read(), "Thread 1").start();
        new Thread(() -> read(), "Thread 2").start();
        new Thread(() -> write(), "Thread 3").start();
        new Thread(() -> read(), "Thread 4").start();
    }

}
