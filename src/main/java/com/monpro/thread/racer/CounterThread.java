package com.monpro.thread.racer;

/**
public class CounterThread extends Thread{

    private static int count = 0;

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for(int i = 0; i < num; i++) {
            threads[i] = new CounterThread();
            threads[i].start();
        }

        for(int i = 0; i < num; i++) {
            threads[i].join();
        }
        // 998836 is printout not equal 1000 * 1000
        // This is due to racer condition
        System.out.println(count);
    }
}
 **/

class Counter {
//    private volatile int count;
    private int count;
    // use synchronized to make the operation atom
//    public synchronized void increment() {
    public void increment() {
        synchronized (this) {
            count += 1;
        }
    }
//    public synchronized int getCount() {
    public int getCount() {
        // the synchronized is try to lock the object not code
        synchronized (this) {
            return count;
        }
    }
}

public class CounterThread extends Thread{
    Counter counter;
    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        int num = 1000;
        Thread[] threads = new Thread[num];
        for(int i = 0; i < num; i++) {
            threads[i] = new CounterThread(counter);
            threads[i].start();
        }

        for(int i = 0; i < num; i++) {
            threads[i].join();
        }
        // it will always get 1000 * 1000
        System.out.println(counter.getCount());
    }
}