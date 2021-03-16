package com.monpro.generics.array;

import java.util.ArrayDeque;
import java.util.Queue;

class MovingAverageInArray {
  private Queue<Integer> queue;
  private double sum;
  private int limit;

  MovingAverageInArray(int size) {

    queue = new ArrayDeque<>();
    sum = 0;
    limit = size;
  }

  double next(int val) {
    if (limit == queue.size()) {
      sum -= queue.poll();
    }
    sum += val;
    queue.add(val);
    return sum / queue.size();
  }
}
