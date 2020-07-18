package com.monpro.thread.racer;

import com.monpro.thread.latch.Latch;

public class RacerWithLatch {
    static class Racer extends Thread{
        Latch latch;

        public Racer(Latch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            try{
                this.latch.await();
                System.out.println("thread: " + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        Latch latch = new Latch(1);
        Thread[] races = new Thread[10];

        for(int i = 0; i < num; i++){
            races[i] = new Racer(latch);
            races[i].start();
        }
        Thread.sleep(2000);
        latch.countDown();
    }
}
