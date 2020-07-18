package com.monpro.thread.latch;

public class Worker extends Thread {
    Latch latch;

    public Worker(Latch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            //simulation worker running
            Thread.sleep((int)(Math.random() * 1000));
            System.out.println("worker: " + Thread.currentThread().getName());
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
