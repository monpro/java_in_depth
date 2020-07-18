package com.monpro.thread.producer_consumer;

public class Consumer extends Thread {
    BlockingQueue<String> queue = null;

    public Consumer(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true){
                String task = queue.take();
                System.out.println("handle task " + task);
                Thread.sleep((int)(Math.random() * 100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
