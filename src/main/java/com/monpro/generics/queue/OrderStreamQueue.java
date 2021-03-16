package com.monpro.generics.queue;

import java.util.ArrayList;
import java.util.List;

class OrderStreamQueue {
  private int cur;
  private String[] stream;

  public OrderStreamQueue(int n) {
    cur = 0;
    stream = new String[n];
  }

  public List<String> insert(int id, String value) {
    List<String> list = new ArrayList<>();

    stream[id - 1] = value;
    while (cur < stream.length && stream[cur] != null) {
      list.add(stream[cur++]);
    }
    return list;
  }
}
