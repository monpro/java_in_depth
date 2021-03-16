package com.monpro.thread.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ConcurrentMapPerformance {

  private static int LOOP_COUNT = 10000000;
  private static int THREAD_COUNT = 10;
  private static int ITEM_COUNT = 10;

  private static Map<String, Long> withSync() throws InterruptedException {
    ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>();
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(
                    i -> {
                      String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                      synchronized (freqs) {
                        if (freqs.contains(key)) {
                          freqs.put(key, freqs.get(key) + 1);
                        } else {
                          freqs.put(key, 1L);
                        }
                      }
                    }));
    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
    return freqs;
  }

  private static Map<String, Long> WithComputeIfAbsent() throws InterruptedException {
    ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>();
    ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);

    forkJoinPool.execute(
        () ->
            IntStream.rangeClosed(1, LOOP_COUNT)
                .parallel()
                .forEach(
                    i -> {
                      String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                      freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
                    }));

    forkJoinPool.shutdown();
    forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

    return freqs.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
  }

  public static void main(String[] args) throws InterruptedException {
    long startTimeWithSync = System.currentTimeMillis();
    Map<String, Long> map = withSync();
    long endTimeWithSync = System.currentTimeMillis();
    System.out.println("consume time: " + (endTimeWithSync - startTimeWithSync));

    long startTimeWithCompute = System.currentTimeMillis();
    Map<String, Long> computeMap = WithComputeIfAbsent();
    long endTimeWithCompute = System.currentTimeMillis();
    System.out.println("consume time " + (endTimeWithCompute - startTimeWithCompute));
  }
}
