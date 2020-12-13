package com.monpro.thread.threadlocal;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadLocalUsageTwoThreads {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++) {
            final int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsageTwoThreads().getDate(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }

    public String getDate(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
