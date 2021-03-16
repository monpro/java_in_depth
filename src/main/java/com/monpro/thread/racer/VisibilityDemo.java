package com.monpro.thread.racer;

class VisibilityDemo {

  private static boolean shutdown = false;
  //    private static volatile boolean shutdown = false;

  public static void main(String[] args) throws InterruptedException {
    new TestThread().start();
    Thread.sleep(1000);
    // this shutdown = true will not visible to the TestThread
    // so it would be a dead loop
    // use volatile would force the variable written into memory instead of cache
    shutdown = true;
    System.out.println("exit main");
  }

  static class TestThread extends Thread {
    @Override
    public void run() {
      while (!shutdown) {}
      System.out.println("exit TestThread");
    }
  }
}
