package com.monpro.thread;

import java.util.ArrayDeque;
import java.util.Queue;

public class BlockingQueue<E> {
    private Queue<E> queue = null;
    private int limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
        this.queue = new ArrayDeque<>(limit);
    }

    public synchronized void put(E e) throws InterruptedException {
        while(queue.size() == limit){
            wait();
        }
        queue.add(e);
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }

        E e = queue.poll();
        notifyAll();
        return e;
    }

    public static void main(String[] args) {
        BlockingQueue<String> queue = new BlockingQueue<>(20);
        new Producer(queue).start();
        new Consumer(queue).start();
    }

}
