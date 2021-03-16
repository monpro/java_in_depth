package com.monpro.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class FixedThreadPoolCase {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    ExecutorService executorServiceOutOfMemory = Executors.newFixedThreadPool(1);
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);
    scheduledExecutorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    //        for(int i = 0; i < 100; i++) {
    //            executorService.execute(new Task());
    //        }

    // this will trigger out of memory error
    // because default is LinkedBlockingQueue
    //        for(int i = 0; i < Integer.MAX_VALUE; i++) {
    //            executorServiceOutOfMemory.execute(new TaskOutOfMemory());
    //        }
  }
}

class Task implements Runnable {
  @Override
  public void run() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName());
  }
}

class TaskOutOfMemory implements Runnable {
  @Override
  public void run() {
    try {
      Thread.sleep(50000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName());
  }
}
