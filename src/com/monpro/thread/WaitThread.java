package com.monpro.thread;

/***
 *  wait and notify are called by different threads
 *  but they are in the same synchronized code block
 *  they share same 'condition"
 *  wait to suspend a thread
 *  notify to wake a thread up
 */
public class WaitThread extends Thread{
    private volatile boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this){
                while(!fire){
                    wait();
                }
            }
            System.out.println("fired");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void fire(){
        this.fire = true;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }
}
