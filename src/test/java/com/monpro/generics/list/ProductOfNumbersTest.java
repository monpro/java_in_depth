package com.monpro.generics.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductOfNumbersTest {

  @Test
  void productOfNumbersTest() {
    ProductOfNumbers product = new ProductOfNumbers();
    product.add(2);
    product.add(3);
    assertEquals(product.getProduct(2), 6);
    product.add(0);
    product.add(4);
    product.add(5);
    assertEquals(product.getProduct(2), 20);
    assertEquals(product.getProduct(4), 0);
  }
}
