package com.monpro.thread.wait;

class Consumer extends Thread {
  private BlockingQueue<String> queue;

  public Consumer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      while (true) {
        String task = queue.take();
        System.out.println("handle task " + task);
        Thread.sleep((int) (Math.random() * 100));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
