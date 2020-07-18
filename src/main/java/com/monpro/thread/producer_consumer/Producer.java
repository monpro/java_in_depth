package com.monpro.thread.producer_consumer;


public class Producer extends Thread {
    BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        int num = 0;
        try{
            while(true){
                String task = String.valueOf(num);
                queue.put(task);
                System.out.println("producer task " + task);
                num++;
                Thread.sleep((int)(Math.random() * 100));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
