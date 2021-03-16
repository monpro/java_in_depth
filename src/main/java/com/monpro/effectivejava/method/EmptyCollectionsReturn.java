package com.monpro.effectivejava.method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class EmptyCollectionsReturn {

  private final List<Integer> stocks = new ArrayList<>();

  /**
   * This is not a good design you always need to use explicit null check to handle the possibly
   * null value
   *
   * @return a list containing all of the stocks or null if there is no stocks
   */
  public List<Integer> getStocks() {
    return stocks.isEmpty() ? null : new ArrayList<>(stocks);
  }

  /**
   * This removes the need of null check logic
   *
   * @return a list containing all of the stocks
   */
  public List<Integer> getStocksWithoutNull() {
    return new ArrayList<>(stocks);
  }

  /**
   * This optimisation avoid the allocations by returning the same immutable empty collection
   * repeatedly, as immutable objects may be shared freely
   *
   * @return a list containing all of the stocks
   */
  public List<Integer> getStocksOptimisation() {
    return stocks.isEmpty() ? Collections.emptyList() : new ArrayList<>(stocks);
  }

  public int[] getStocksArray() {
    return stocks.stream().mapToInt(i -> i).toArray();
  }
}
