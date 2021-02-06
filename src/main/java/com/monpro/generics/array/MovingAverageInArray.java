package com.monpro.generics.array;

import java.util.ArrayDeque;
import java.util.Queue;

public class MovingAverageInArray {
  private Queue<Integer> queue;
  private double sum;
  private int limit;

  public MovingAverageInArray(int size) {

    queue = new ArrayDeque<>();
    sum = 0;
    limit = size;
  }

  public double next(int val) {
    if (limit == queue.size()) {
      sum -= queue.poll();
    }
    sum += val;
    queue.add(val);
    return sum / queue.size();
  }
}
