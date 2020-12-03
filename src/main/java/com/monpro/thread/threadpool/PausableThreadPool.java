package com.monpro.thread.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PausableThreadPool extends ThreadPoolExecutor {

    private boolean isPaused;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private Condition unpaused = reentrantLock.newCondition();

    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PausableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        reentrantLock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }}
        catch (InterruptedException e) {
                e.printStackTrace();
            }
        finally {
            reentrantLock.unlock();
        }
    }

    private void pause() {
        reentrantLock.lock();
        try {
            isPaused = true;
        } finally {
            reentrantLock.unlock();
        }
    }

    private void resume() {
        reentrantLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PausableThreadPool pausableThreadPool = new PausableThreadPool(
                10,
                20,
                100,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for(int i = 0; i < 10000; i++) {
            pausableThreadPool.execute(runnable);
        }
        Thread.sleep(1500);
        pausableThreadPool.pause();
        System.out.println("thread pool is paused");
        Thread.sleep(1500);
        pausableThreadPool.resume();
        System.out.println("thread pool is restarted");

    }

}
