package com.monpro.generics.queue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStreamQueueTest {

  @Test
  void OrderStreamQueueOperationTest() {
    OrderStreamQueue stream = new OrderStreamQueue(5);
    assertEquals(stream.insert(4, "ddd"), Arrays.asList());
    assertEquals(stream.insert(1, "aaa"), Arrays.asList("aaa"));
    assertEquals(stream.insert(3, "ccc"), Arrays.asList());
    assertEquals(stream.insert(2, "bbb"), Arrays.asList("bbb", "ccc", "ddd"));
  }
}
