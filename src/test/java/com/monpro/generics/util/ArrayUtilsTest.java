package com.monpro.generics.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {
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
    float[] bags = new float[] {1.01f, 2.21f, 1.30f};
    assertEquals(ArrayUtils.throwCount(bags, 3.00f), 2);
  }

  @Test
  void canPartitionTest() {
    int[] num = new int[] {2, 4, 5, 3, 3, 9, 2, 2, 2};
    assertEquals(ArrayUtils.canPartition(num), true);
  }

  @Test
  void convertToPostfixNotationTest() {
    assertEquals(
        ArrayUtils.convertToPostfixNotation(new String[] {"3", "-", "4", "+", "5"}).toString(),
        new ArrayList<>(Arrays.asList("3", "4", "-", "5", "+")).toString());
    assertEquals(
        ArrayUtils.convertToPostfixNotation(new String[] {"(", "3", "-", "4", ")", "*", "5"})
            .toString(),
        new ArrayList<>(Arrays.asList("3", "4", "-", "5", "*")).toString());
    assertEquals(
        ArrayUtils.convertToPostfixNotation(new String[] {"3", "-", "4", "*", "5"}).toString(),
        new ArrayList<>(Arrays.asList("3", "4", "5", "*", "-")).toString());
  }

  @Test
  void getSumOfIntervalsTest() {
    Interval interval1 = new Interval(1, 3);
    Interval interval2 = new Interval(2, 4);
    Interval interval3 = new Interval(1, 4);

    assertEquals(
        ArrayUtils.getSumOfIntervals(
                new int[] {1, 2, 3, 4, 5},
                new ArrayList<Interval>(Arrays.asList(interval1, interval2, interval3)))
            .toString(),
        new ArrayList<Long>(Arrays.asList(9L, 12L, 14L)).toString());
  }

  @Test
  void bipartitionArrayTest() {
    assertEquals(ArrayUtils.bipartitionArray(4, new int[][] {{1, 2}, {1, 3}, {2, 4}}), true);
    assertEquals(ArrayUtils.bipartitionArray(4, new int[][] {{1, 2}, {1, 3}}), true);
    assertEquals(
        ArrayUtils.bipartitionArray(4, new int[][] {{1, 2}, {1, 3}, {2, 4}, {1, 4}}), false);

    assertEquals(
        ArrayUtils.bipartitionArrayTraverse(4, new int[][] {{1, 2}, {1, 3}, {2, 4}}), true);
    assertEquals(ArrayUtils.bipartitionArrayTraverse(4, new int[][] {{1, 2}, {1, 3}}), true);
    assertEquals(
        ArrayUtils.bipartitionArrayTraverse(4, new int[][] {{1, 2}, {1, 3}, {2, 4}, {1, 4}}),
        false);
  }

  @Test
  void smallestSetOfStartingPointsTest() {
    List<List<Integer>> edges = new ArrayList<>();
    edges.add(new ArrayList<>(Arrays.asList(0, 1)));
    edges.add(new ArrayList<>(Arrays.asList(0, 2)));
    edges.add(new ArrayList<>(Arrays.asList(2, 5)));
    edges.add(new ArrayList<>(Arrays.asList(3, 4)));
    edges.add(new ArrayList<>(Arrays.asList(4, 2)));
    assertEquals(
        ArrayUtils.smallestSetOfStartingPoints(6, edges).toString(),
        new ArrayList<>(Arrays.asList(0, 3)).toString());
  }

  @Test
  void isPossibleEquationsTest() {
    assertEquals(ArrayUtils.isPossibleEquations(new String[] {"a==b", "b!=a"}), false);
    assertEquals(ArrayUtils.isPossibleEquations(new String[] {"a==b", "b==a"}), true);
  }

  @Test
  void minimumCostForConnectionsTest() {
    assertEquals(
        ArrayUtils.minimumCostForConnections(3, new int[][] {{1, 2, 5}, {1, 3, 6}, {2, 3, 1}}), 6);
    assertEquals(
        ArrayUtils.minimumCostForConnections(4, new int[][] {{1, 4, 5}, {1, 3, 6}, {3, 4, 1}}), -1);
  }

  @Test
  void getDelayTimeTest() {
    assertEquals(ArrayUtils.getDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2), 2);
  }

  @Test
  void isValidPathForStreetsTest() {
    assertEquals(ArrayUtils.isValidPathForStreets(new int[][] {{2, 4, 3}, {6, 5, 2}}), true);
    assertEquals(ArrayUtils.isValidPathForStreets(new int[][] {{1, 2, 1}, {6, 5, 2}}), false);
  }

  @Test
  void sumOddLengthArrayTest() {
    assertEquals(ArrayUtils.sumOddLengthArray(new int[] {1, 2, 3, 4, 5}), 57);
    assertEquals(ArrayUtils.sumOddLengthArray(new int[] {}), 0);
    assertEquals(ArrayUtils.sumOddLengthArray(new int[] {1, 2, 3, 4, 55}), 207);
  }

  @Test
  void sumEvenLengthArrayTest() {
    assertEquals(ArrayUtils.sumEvenLengthArray(new int[] {1, 2, 3, 4, 5}), 48);
    assertEquals(ArrayUtils.sumEvenLengthArray(new int[] {}), 0);
    assertEquals(ArrayUtils.sumEvenLengthArray(new int[] {1, 2, 3, 4, 55}), 148);
  }

  @Test
  void rankTeamsTest() {
    assertEquals(ArrayUtils.rankTeams(new String[] {"ABC", "ACB", "ABC", "ACB", "ACB"}), "ACB");
    assertEquals(ArrayUtils.rankTeams(new String[] {"WXYZ", "XYZW"}), "XWYZ");
    assertEquals(
        ArrayUtils.rankTeams(new String[] {"BCA", "CAB", "CBA", "ABC", "ACB", "BAC"}), "ABC");
  }

  @Test
  void getMaxSubArrayWithPositiveProductTest() {
    assertEquals(ArrayUtils.getMaxSubArrayWithPositiveProduct(new int[] {1, -2, -3, 4}), 4);
    assertEquals(ArrayUtils.getMaxSubArrayWithPositiveProduct(new int[] {0, 1, -2, -3, -4}), 3);
    assertEquals(ArrayUtils.getMaxSubArrayWithPositiveProduct(new int[] {-1, -2, -3, 0, 1}), 2);
  }

  @Test
  void drawColorOnBorderTest() {
    assertArrayEquals(
        ArrayUtils.drawColorOnBorder(new int[][] {{1, 1}, {1, 2}}, 0, 0, 3),
        new int[][] {{3, 3}, {3, 2}});
  }

  @Test
  void numsSameDiffTest() {
    for (int i = 2; i < 9; i++) {
      assertArrayEquals(ArrayUtils.numsSameDiffBfs(i, 7), ArrayUtils.numsSameConsecDiffDfs(i, 7));
    }
    assertArrayEquals(ArrayUtils.numsSameDiffBfs(3, 7), new int[] {181, 292, 707, 818, 929});
    assertArrayEquals(ArrayUtils.numsSameConsecDiffDfs(3, 7), new int[] {181, 292, 707, 818, 929});
  }

  @Test
  void numSubMatrixTest() {
    assertEquals(ArrayUtils.numSubMatrix(new int[][] {{1}}), 1);
    assertEquals(ArrayUtils.numSubMatrix(new int[][] {{1, 1, 1, 1}}), 10);
    assertEquals(ArrayUtils.numSubMatrix(new int[][] {{1, 1, 1, 1}, {1, 1, 1, 1}}), 30);
    assertEquals(ArrayUtils.numSubMatrix(new int[][] {{1, 1, 1, 0}, {1, 0, 1, 1}}), 12);
  }

  @Test
  void canSplitArrayIntoConsecutiveSequencesTest() {
    assertEquals(
        ArrayUtils.canSplitArrayIntoConsecutiveSequences(new int[] {1, 2, 3, 3, 4, 5}), true);
    assertEquals(ArrayUtils.canSplitArrayIntoConsecutiveSequences(new int[] {1, 2, 3, 4, 5}), true);
    assertEquals(
        ArrayUtils.canSplitArrayIntoConsecutiveSequences(new int[] {1, 4, 2, 3, 4, 5}), false);
    assertEquals(
        ArrayUtils.canSplitArrayIntoConsecutiveSequences(new int[] {1, 2, 3, 4, 4, 5}), false);
    assertEquals(ArrayUtils.canSplitArrayIntoConsecutiveSequences(new int[] {}), false);
  }

  @Test
  void buildStackOperationArrayTest() {
    assertEquals(ArrayUtils.buildStackOperationArray(null, 1), new ArrayList<>());
    assertEquals(ArrayUtils.buildStackOperationArray(new int[] {}, 1), new ArrayList<>());
    assertEquals(ArrayUtils.buildStackOperationArray(new int[] {1, 3}, -1), new ArrayList<>());
    List<Integer> list = new ArrayList<>();
    assertEquals(
        ArrayUtils.buildStackOperationArray(new int[] {1, 3}, 3),
        new ArrayList<>(Arrays.asList("Push", "Push", "Pop", "Push")));
  }

  @Test
  void smallestSubSequenceTest() {
    assertArrayEquals(ArrayUtils.smallestSubSequence(new int[] {}, 1), new int[] {});
    assertArrayEquals(ArrayUtils.smallestSubSequence(new int[] {1, 3}, -1), new int[] {});
    assertArrayEquals(ArrayUtils.smallestSubSequence(new int[] {3, 5, 2, 6}, 2), new int[] {2, 6});
    assertArrayEquals(
        ArrayUtils.smallestSubSequence(new int[] {2, 4, 3, 3, 5, 4, 9, 6}, 4),
        new int[] {2, 3, 3, 4});
  }

  @Test
  void maximumAscSubArraySumTest() {
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.findMaxConsecutiveOnes(null));
    assertTrue(exception.getMessage().contains("Please check the array"));
    assertEquals(ArrayUtils.maximumAscSubArraySum(new int[] {10, 20, 30, 5, 10, 50}), 65);
  }

  @Test
  void findMaxConsecutiveOnesTest() {
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> ArrayUtils.findMaxConsecutiveOnes(null));
    assertTrue(exception.getMessage().contains("Please check the array"));
    assertEquals(ArrayUtils.findMaxConsecutiveOnes(new int[] {1, 0, 1, 1, 0}), 4);
  }

  @Test
  void findMaxConsecutiveOnesWithKZerosTest() {
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> ArrayUtils.findMaxConsecutiveOnesWithKZeros(null, 1));
    assertTrue(exception.getMessage().contains("Please check the array"));
    assertEquals(ArrayUtils.findMaxConsecutiveOnesWithKZeros(new int[] {1, 0, 1, 1, 0}, 1), 4);
  }

  @Test
  void numSubArraysWithSumTest() {
    assertEquals(ArrayUtils.numSubArraysWithSum(new int[] {1, 0, 1, 0, 1}, 2), 4);
  }

  @Test
  void maxChunksToSortedTest() {
    assertEquals(ArrayUtils.maxChunksToSorted(new int[]{3, 1 , 2, 0}), 1);
    assertEquals(ArrayUtils.maxChunksToSorted(new int[]{1, 0, 3, 2, 4}), 3);
  }

  @Test
  void maxChunksToSortedIITest(){
    assertEquals(ArrayUtils.maxChunksToSortedII(new int[]{3, 11 , 2, 1}), 1);
    assertEquals(ArrayUtils.maxChunksToSortedII(new int[]{1, 3 , 4, 6, 9, 8}), 5);
  }

  @Test
  void maximumUnitsTest() {
    assertEquals(ArrayUtils.maximumUnits(new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4), 8);
    assertEquals(ArrayUtils.maximumUnitsBucketSort(new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4), 8);
  }
}
