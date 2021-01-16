package com.monpro.generics.util;

import java.util.*;

class Point {
  int x;
  int y;
  int val;

  Point(int x, int y, int val) {
    this.x = x;
    this.y = y;
    this.val = val;
  }
}

public class ArrayUtils {
  private ArrayUtils() {}

  public static boolean skipToLastIndex(int[] nums) {
    /**
     * O(n^2) solution
     *
     * <p>boolean[] dp = new boolean[nums.length]; dp[0] = true; int n = nums.length; for(int i = 1;
     * i < n; i++) { for(int j = 0; j < i; j++) { if(dp[j] == true&& j + nums[j] >= i) { dp[i] =
     * true; } } } return dp[n - 1];
     */
    int maxSkipPosition = 0;
    for (int i = 0; i < nums.length; i++) {
      if (maxSkipPosition < i) {
        return false;
      }
      if (i + nums[i] > maxSkipPosition) {
        maxSkipPosition = i + nums[i];
      }
    }
    return true;
  }

  public static int minStepsToLastIndex(int[] nums) {
    int result = 0;
    int curFurthest = 0, curEnd = 0;

    for (int cur = 0; cur < nums.length - 1; cur++) {
      curFurthest = Math.max(cur + nums[cur], curFurthest);
      if (cur == curEnd) {
        result += 1;
        curEnd = curFurthest;
      }
    }
    return result;
  }

  public static boolean canReachToIndexWithValueZero(int[] nums, int start) {
    Stack<Integer> stack = new Stack<>();
    Set<Integer> set = new HashSet<>();
    stack.add(start);

    while (!stack.isEmpty()) {
      int index = stack.pop();
      if (nums[index] == 0) {
        return true;
      }
      int left = index - nums[index];
      int right = index + nums[index];
      if (left >= 0 && !set.contains(left)) {
        stack.add(left);
        set.add(left);
      }
      if (right < nums.length && !set.contains(right)) {
        stack.add(right);
        set.add(right);
      }
    }
    return false;
  }

  public static int shortestPathInBinaryMatrix(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
      return -1;
    }
    boolean[][] visited = new boolean[m][n];
    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(0, 0, 1));
    int[][] directions =
        new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};

    while (!queue.isEmpty()) {
      int size = queue.size();
      Point point = queue.poll();
      if (point.x == m - 1 && point.y == n - 1) {
        return point.val;
      }
      for (int[] direction : directions) {
        int nextX = point.x + direction[0];
        int nextY = point.y + direction[1];
        if (isValid(grid, visited, nextX, nextY)) {
          queue.add(new Point(nextX, nextY, point.val + 1));
          grid[nextX][nextY] = 1;
        }
      }
    }
    return -1;
  }

  public static boolean isValid(int[][] grid, boolean[][] visited, int x, int y) {
    int m = grid.length, n = grid[0].length;
    return x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && grid[x][y] == 0;
  }
}
