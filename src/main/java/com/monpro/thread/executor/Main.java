package com.monpro.thread.executor;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    static class Task implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            int sleepSeconds = new Random().nextInt(1000);
            Thread.sleep(sleepSeconds);
            return sleepSeconds;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = new ExecutorService();
        Future<Integer> future = executor.submit(new Task());

        //other task
        Thread.sleep(1000);
        try{
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
