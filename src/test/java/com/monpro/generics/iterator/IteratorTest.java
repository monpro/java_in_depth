package com.monpro.generics.iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IteratorTest {
  @Test
  void PeekingIteratorTest() {
    Iterator<Integer> iterator = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)).iterator();
    PeekingIterator<Integer> peekingIterator = new PeekingIterator<>(iterator);
    assertEquals(peekingIterator.peek(), 1);
    assertEquals(peekingIterator.next(), 1);
    assertEquals(peekingIterator.hasNext(), true);
    assertEquals(peekingIterator.peek(), 2);
  }
}
