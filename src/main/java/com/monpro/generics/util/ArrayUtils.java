package com.monpro.generics.util;

import java.util.*;

class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
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

  public static int getSumOfDiagonal(int[][] mat) {
    int result = 0;
    int x = 0, y = 0;
    int m = mat.length;
    while (x < m && y < m) {
      result += mat[x][y];
      x += 1;
      y += 1;
    }

    x = 0;
    y = m - 1;
    while (x < m && y >= 0) {
      result += mat[x][y];
      x += 1;
      y -= 1;
    }

    if (m % 2 == 1) {
      result -= mat[m / 2][m / 2];
    }
    return result;
  }

  public static int[] transfromCells(int[] cells, int N) {
    Map<String, Integer> cache = new HashMap<>();
    while (N > 0) {
      int[] nextCells = new int[8];
      cache.put(Arrays.toString(cells), N);
      N -= 1;
      for (int i = 1; i < 7; i++) {
        nextCells[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
      }
      cells = nextCells;
      String key = Arrays.toString(cells);
      if (cache.containsKey(key)) {
        N %= cache.get(key) - N;
      }
    }
    return cells;
  }

  public static int maxPointsOnSameLine(Point[] points) {
    if (points == null) {
      return 0;
    }
    int result = 0;
    Map<String, Integer> count = new HashMap<>();
    for(int i = 0; i < points.length; i++) {
      count.clear();
      int sameLine = 0, vertical = 0, overlap = 0;
      for(int j = i + 1; j < points.length; j++) {
        if(points[j].x == points[i].x) {
          if(points[j].y == points[i].y) {
            overlap += 1;
          } else {
            vertical += 1;
          }
          continue;
        }
        int dy = points[j].y - points[i].y;
        int dx = points[j].x - points[i].x;
        int gcd = getGcd(dy, dx);
        dy /= gcd;
        dx /= gcd;
        String key = dy + "/" + dx;
        count.put(key, count.getOrDefault(key, 0) + 1);
        sameLine = Math.max(sameLine, count.get(key));
      }
      sameLine = Math.max(sameLine, vertical);
      result = Math.max(result, sameLine + overlap + 1);

    }
    return result;
  }

  private static int getGcd(int a, int b) {
    if (b == 0) {
      return a;
    } else {
      return getGcd(b, a % b);
    }
  }
  public static int minimumPointsCombineArray(List<Integer> sticks) {
    // write your code here
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for(Integer stick: sticks) {
      queue.add(stick);
    }
    int result = 0;
    while(queue.size() > 1) {
      Integer stick = queue.poll();
      stick += queue.poll();
      result += stick;
      queue.add(stick);
    }
    return result;
  }

  public static int throwCount(float[] bags, float limit) {
    int left = 0, right = bags.length - 1;
    int result = 0;
    Arrays.sort(bags);

    while(left <= right) {
      while(left < right && bags[left] + bags[right] > limit) {
        right -= 1;
        result += 1;
      }

      if(left == right || bags[left] + bags[right] <= limit) {
        right -= 1;
        left += 1;
        result += 1;
      }
    }
    return result;
  }

  public static boolean canPartition(int[] nums) {
    if (nums.length < 5) return false;
    int prefix = 0, suffix = nums[nums.length - 1];
    Map<Integer, Integer> prefix_sum = new HashMap<>();
    for (int i = 1; i < nums.length - 3; i++) {
      prefix += nums[i - 1];
      prefix_sum.put(prefix, i);
    }
    int total = 0;
    for (int n : nums)
      total += n;
    for (int i = nums.length - 2; i >= 3; i--) {
      if (prefix_sum.containsKey(suffix) && prefix_sum.get(suffix) <= i - 2) {
        int mid = total - 2 * suffix - nums[i] - nums[prefix_sum.get(suffix)];
        if (mid == suffix) return true;
      }
      suffix += nums[i];
    }
    return false;
  }

  public static List<String> convertToPostfixNotation(String[] expression) {
    if (expression == null || expression.length == 0) {
      return null;
    }

    List<String> result = new ArrayList<>();
    Stack<String> stack = new Stack<>();

    for(String str: expression) {
      if (str.equals("(")) {
        stack.push(str);
      }
      else if (str.equals(")")) {
        while (!stack.peek().equals("(")) {
          result.add(stack.pop());
        }
        stack.pop();
      }
      else if(Character.isDigit(str.charAt(0))) {
        result.add(str);
      }
      else {
        // stack only includes symbols
        // we need to add the symbol with highest priority first
        while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(str)) {
          result.add(stack.pop());
        }
        stack.add(str);
      }
    }

    while (!stack.isEmpty()) {
      result.add(stack.pop());
    }

    return result;
  }

  private static int getPriority(String str) {
    if (str.equals("*") || str.equals("/")) {
      return 3;
    } else if(str.equals("+") || str.equals("-")) {
      return 2;
    } else if(str.equals("(")) {
      return 1;
    } return 0;
  }

  public static List<Long> getSumOfIntervals(int[] nums, List<Interval> intervals) {
      List<Long> result = new ArrayList<>();
      if(intervals == null || intervals.size() == 0 || nums == null || nums.length == 0) {
          return result;
      }
      long[] prefixSum = new long[nums.length + 1];
      long prefix = 0;
      for(int i = 1; i < nums.length + 1; i++) {
          prefix += nums[i - 1];
          prefixSum[i] = prefix;
      }
      for(Interval interval: intervals) {
          int start = interval.start;
          int end = interval.end;
          result.add(prefixSum[end + 1] - prefixSum[start]);
      }
      return result;
  }
}
