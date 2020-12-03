package com.monpro.thread.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolShutdown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 20; i++) {
            executorService.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        List<Runnable> runnables = executorService.shutdownNow();
        for(Runnable runnable: runnables) {
            System.out.println(runnable);
        }
        // executorService.shutdown();
        // boolean termination = executorService.awaitTermination(5l, TimeUnit.SECONDS);
        // System.out.println(termination);
        // //isShutDown: whether the threadPool is going to shutDown
        // //isTerminated: whether the threadPool is totally existed
        // shutdownNow:
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
    }
}

class ShutDownTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "interrupted");
            e.printStackTrace();
        }
    }
}