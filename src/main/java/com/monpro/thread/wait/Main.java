package com.monpro.thread.wait;

class Main {
  public static void main(String[] args) throws InterruptedException {
    WaitThread waitThread = new WaitThread();
    waitThread.start();
    Thread.sleep(1000);
    System.out.println("fire");
    waitThread.fire();
  }
}
