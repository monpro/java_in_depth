package com.monpro.generics.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductOfNumbers {
  List<Integer> prefixProduct = new ArrayList<>(Arrays.asList(1));

  public ProductOfNumbers() {}

  public void add(int num) {
    if (num > 0) {
      prefixProduct.add(prefixProduct.get(prefixProduct.size() - 1) * num);
    } else {
      prefixProduct.clear();
      prefixProduct.add(1);
    }
  }

  public int getProduct(int k) {
    int n = prefixProduct.size();
    return n > k ? prefixProduct.get(n - 1) / prefixProduct.get(n - k - 1) : 0;
  }
}
