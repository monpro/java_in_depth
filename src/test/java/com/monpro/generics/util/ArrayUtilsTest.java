package com.monpro.generics.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayUtilsTest {
  @Test
  void skipToLastIndexTest() {
    int[] nums = new int[] {2, 3, 1, 1, 4};
    assertEquals(ArrayUtils.skipToLastIndex(nums), true);
    nums = new int[] {3, 2, 1, 0, 4};
    assertEquals(ArrayUtils.skipToLastIndex(nums), false);
    assertEquals(ArrayUtils.skipToLastIndex(new int[] {}), true);
  }

  @Test
  void minStepsToLastIndexTest() {
    int[] nums = new int[] {2, 3, 1, 1, 4};
    assertEquals(ArrayUtils.minStepsToLastIndex(nums), 2);
    nums = new int[] {3, 2, 1, 0, 4};
    assertEquals(ArrayUtils.minStepsToLastIndex(nums), 2);
    nums = new int[] {2, 6, 1, 0, 4, 5, 4};
    assertEquals(ArrayUtils.minStepsToLastIndex(nums), 2);
  }

  @Test
  void canReachToIndexWithValueZero() {
    int[] nums = new int[] {4, 2, 3, 0, 3, 1, 2};
    int start = 5;
    assertEquals(ArrayUtils.canReachToIndexWithValueZero(nums, start), true);
    nums = new int[] {4, 2, 3, 0, 3, 1, 2};
    start = 0;
    assertEquals(ArrayUtils.canReachToIndexWithValueZero(nums, start), true);
    nums = new int[] {3, 0, 2, 1, 2};
    start = 2;
    assertEquals(ArrayUtils.canReachToIndexWithValueZero(nums, start), false);
  }

  @Test
  void shortestPathInBinaryMatrixTest() {
    assertEquals(ArrayUtils.shortestPathInBinaryMatrix(new int[][] {{0, 1}, {1, 0}}), 2);
    assertEquals(ArrayUtils.shortestPathInBinaryMatrix(new int[][] {{1, 1}, {1, 0}}), -1);
    assertEquals(
        ArrayUtils.shortestPathInBinaryMatrix(new int[][] {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}), 4);
    assertEquals(
        ArrayUtils.shortestPathInBinaryMatrix(new int[][] {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}}), -1);
  }

  @Test
  void getSumOfDiagonalTest() {
    assertEquals(ArrayUtils.getSumOfDiagonal(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}), 25);
    assertEquals(ArrayUtils.getSumOfDiagonal(new int[][] {{7}}), 7);
    assertEquals(ArrayUtils.getSumOfDiagonal(new int[][] {{1, 2}, {3, 4}}), 10);
  }

  @Test
  void transformCellsTest() {
    int[] array = new int[] {0, 1, 0, 1, 1, 0, 0, 1};
    assertEquals(
        Arrays.toString(ArrayUtils.transfromCells(array, 1)),
        Arrays.toString(new int[] {0, 1, 1, 0, 0, 0, 0, 0}));
    assertEquals(
        Arrays.toString(ArrayUtils.transfromCells(array, 7)),
        Arrays.toString(new int[] {0, 0, 1, 1, 0, 0, 0, 0}));
  }

  @Test
  void maxPointsOnSameLineTest() {
      Point[] points = new Point[4];
      points[0] = new Point(1, 2);
      points[1] = new Point(3, 6);
      points[2] = new Point(0, 0);
      points[3] = new Point(1, 3);

      assertEquals(ArrayUtils.maxPointsOnSameLine(points), 3);
  }

  @Test
  void minimumPointsCombineArrayTest() {
      List<Integer> list = new ArrayList<>(Arrays.asList(3, 7, 5, 12, 18, 4));
      assertEquals(ArrayUtils.minimumPointsCombineArray(list), 117);
  }

  @Test
  void throwCountTest() {
      float[] bags = new float[]{1.01f,2.21f,1.30f};
      assertEquals(ArrayUtils.throwCount(bags, 3.00f), 2);
  }

  @Test
  void canPartitionTest() {
      int[] num = new int[]{2, 4, 5, 3, 3, 9, 2, 2, 2};
      assertEquals(ArrayUtils.canPartition(num), true);
  }
}
