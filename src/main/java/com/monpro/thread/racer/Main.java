package com.monpro.thread.racer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int nums = 10;
        FireFlag fireFlag = new FireFlag();
        Thread[] racers = new Thread[nums];
        for(int i = 0; i < nums; i++){
            racers[i] = new Racer(fireFlag);
            racers[i].start();
        }
        Thread.sleep(1000);
        System.out.println("ready to fire");
        fireFlag.fire();
    }
}
