package com.monpro.thread.future;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
       Executor executor = new Executor();
        Callable<Integer> subTask = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                int mills = (int)(Math.random() * 1000);
                Thread.sleep(mills);
                return mills;
            }
        };
        System.out.println("start thread");
        Future<Integer> future = executor.execute(subTask);
        try{
            Integer result = future.get();
            System.out.println("get result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
