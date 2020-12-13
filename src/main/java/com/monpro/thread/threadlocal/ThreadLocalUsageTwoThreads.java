package com.monpro.thread.threadlocal;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadLocalUsageTwoThreads {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    // if use global object, not thread safe - all threads use the same object
    // we could use Lock to enable thread safe
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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
        String result = "";
        synchronized (ThreadLocalUsageTwoThreads.class) {
            result = dateFormat.format(date);
        }
        return result;
    }
}
