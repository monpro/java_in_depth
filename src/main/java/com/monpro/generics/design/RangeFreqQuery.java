package com.monpro.generics.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class RangeFreqQuery {
  Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();
  // array [1,2,2,3,2]
  // map: {1: {0: 0}, {2:{1: 0, 2 : 1, 4 : 2}}, {3: {3 : 0}}}
  // if query(1, 4, 2) - 3
  // if query(0, 3, 2) - 2

  public RangeFreqQuery(int[] arr) {
    for(int i = 0; i < arr.length;i++){
      map.putIfAbsent(arr[i], new TreeMap<>());
      map.get(arr[i]).put(i, map.get(arr[i]).size());
    }

  }

  public int query(int left, int right, int value) {
    if(!map.containsKey(value)) return 0;
    TreeMap<Integer, Integer> nums = map.get(value);
    Integer a = nums.ceilingKey(left), b = nums.floorKey(right);
    if(a == null || b == null) return 0;
    return nums.get(b) - nums.get(a) +1;
  }
}