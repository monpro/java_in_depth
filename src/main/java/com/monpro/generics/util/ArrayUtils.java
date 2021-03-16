package com.monpro.generics.util;

import java.util.*;

class Interval {
  int start, end;

  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

class ArrayUtils {
  private ArrayUtils() {}

  static boolean skipToLastIndex(int[] nums) {
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

  static int minStepsToLastIndex(int[] nums) {
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

  static boolean canReachToIndexWithValueZero(int[] nums, int start) {
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

  static int shortestPathInBinaryMatrix(int[][] grid) {
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

  private static boolean isValid(int[][] grid, boolean[][] visited, int x, int y) {
    int m = grid.length, n = grid[0].length;
    return x >= 0 && x <= m - 1 && y >= 0 && y <= n - 1 && grid[x][y] == 0;
  }

  static int getSumOfDiagonal(int[][] mat) {
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

  static int[] transfromCells(int[] cells, int N) {
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

  static int maxPointsOnSameLine(Point[] points) {
    if (points == null) {
      return 0;
    }
    int result = 0;
    Map<String, Integer> count = new HashMap<>();
    for (int i = 0; i < points.length; i++) {
      count.clear();
      int sameLine = 0, vertical = 0, overlap = 0;
      for (int j = i + 1; j < points.length; j++) {
        if (points[j].x == points[i].x) {
          if (points[j].y == points[i].y) {
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

  static int minimumPointsCombineArray(List<Integer> sticks) {
    // write your code here
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (Integer stick : sticks) {
      queue.add(stick);
    }
    int result = 0;
    while (queue.size() > 1) {
      Integer stick = queue.poll();
      stick += queue.poll();
      result += stick;
      queue.add(stick);
    }
    return result;
  }

  static int throwCount(float[] bags, float limit) {
    int left = 0, right = bags.length - 1;
    int result = 0;
    Arrays.sort(bags);

    while (left <= right) {
      while (left < right && bags[left] + bags[right] > limit) {
        right -= 1;
        result += 1;
      }

      if (left == right || bags[left] + bags[right] <= limit) {
        right -= 1;
        left += 1;
        result += 1;
      }
    }
    return result;
  }

  static boolean canPartition(int[] nums) {
    if (nums.length < 5) return false;
    int prefix = 0, suffix = nums[nums.length - 1];
    Map<Integer, Integer> prefix_sum = new HashMap<>();
    for (int i = 1; i < nums.length - 3; i++) {
      prefix += nums[i - 1];
      prefix_sum.put(prefix, i);
    }
    int total = 0;
    for (int n : nums) total += n;
    for (int i = nums.length - 2; i >= 3; i--) {
      if (prefix_sum.containsKey(suffix) && prefix_sum.get(suffix) <= i - 2) {
        int mid = total - 2 * suffix - nums[i] - nums[prefix_sum.get(suffix)];
        if (mid == suffix) return true;
      }
      suffix += nums[i];
    }
    return false;
  }

  static List<String> convertToPostfixNotation(String[] expression) {
    if (expression == null || expression.length == 0) {
      return null;
    }

    List<String> result = new ArrayList<>();
    Stack<String> stack = new Stack<>();

    for (String str : expression) {
      if (str.equals("(")) {
        stack.push(str);
      } else if (str.equals(")")) {
        while (!stack.peek().equals("(")) {
          result.add(stack.pop());
        }
        stack.pop();
      } else if (Character.isDigit(str.charAt(0))) {
        result.add(str);
      } else {
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
    } else if (str.equals("+") || str.equals("-")) {
      return 2;
    } else if (str.equals("(")) {
      return 1;
    }
    return 0;
  }

  static List<Long> getSumOfIntervals(int[] nums, List<Interval> intervals) {
    List<Long> result = new ArrayList<>();
    if (intervals == null || intervals.size() == 0 || nums == null || nums.length == 0) {
      return result;
    }
    long[] prefixSum = new long[nums.length + 1];
    long prefix = 0;
    for (int i = 1; i < nums.length + 1; i++) {
      prefix += nums[i - 1];
      prefixSum[i] = prefix;
    }
    for (Interval interval : intervals) {
      int start = interval.start;
      int end = interval.end;
      result.add(prefixSum[end + 1] - prefixSum[start]);
    }
    return result;
  }

  static boolean bipartitionArray(int N, int[][] differentGroup) {
    // if we tried to do the partition
    // then dislike[i] cannot be in same group
    // we need to find the dislike mapping
    // [1: {2, 3}]
    Map<Integer, List<Integer>> mapping = new HashMap<>();
    for (int i = 1; i <= N; i++) {
      mapping.put(i, new ArrayList<Integer>());
    }

    for (int[] dislike : differentGroup) {
      int dislikeFront = dislike[0], dislikeEnd = dislike[1];
      List<Integer> frontList = mapping.get(dislikeFront);
      List<Integer> endList = mapping.get(dislikeEnd);
      frontList.add(dislikeEnd);
      endList.add(dislikeFront);
      mapping.put(dislikeFront, frontList);
      mapping.put(dislikeEnd, endList);
    }
    // 1 means first group
    // -1 means second group
    int[] group = new int[N + 1];
    for (int i = 1; i < N; i++) {
      if (group[i] == 0 && !dfs(mapping, group, i, -1)) {
        return false;
      }
    }
    return true;
  }

  private static boolean dfs(
      Map<Integer, List<Integer>> mapping, int[] group, int index, int color) {
    if (group[index] == 0) {
      group[index] = color;
      int anotherColor = color == -1 ? 1 : -1;
      for (int i : mapping.get(index)) {
        if (!dfs(mapping, group, i, anotherColor)) {
          return false;
        }
      }
    } else {
      return group[index] == color;
    }
    return true;
  }

  static boolean bipartitionArrayTraverse(int N, int[][] differentGroup) {
    // if we tried to do the partition
    // then dislike[i] cannot be in same group
    // we need to find the dislike mapping
    // [1: {2, 3}]
    Map<Integer, List<Integer>> mapping = new HashMap<>();
    for (int i = 1; i <= N; i++) {
      mapping.put(i, new ArrayList<Integer>());
    }

    for (int[] dislike : differentGroup) {
      int dislikeFront = dislike[0], dislikeEnd = dislike[1];
      List<Integer> frontList = mapping.get(dislikeFront);
      List<Integer> endList = mapping.get(dislikeEnd);
      frontList.add(dislikeEnd);
      endList.add(dislikeFront);
      mapping.put(dislikeFront, frontList);
      mapping.put(dislikeEnd, endList);
    }
    // 1 means first group
    // -1 means second group
    int[] group = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      if (group[i] == 0) {
        group[i] = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while (!queue.isEmpty()) {
          int node = queue.poll();
          for (int dislike : mapping.get(node)) {
            if (group[dislike] == 0) {
              group[dislike] = group[node] == 1 ? -1 : 1;
              queue.offer(dislike);
            } else {
              if (group[dislike] == group[node]) {
                return false;
              }
            }
          }
        }
      }
    }
    return true;
  }

  static List<Integer> smallestSetOfStartingPoints(int n, List<List<Integer>> edges) {
    List<Integer> result = new ArrayList<>();
    int[] visited = new int[n];
    for (List<Integer> edge : edges) {
      visited[edge.get(1)] = 1;
    }
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        result.add(i);
      }
    }
    return result;
  }

  static boolean isPossibleEquations(String[] equations) {
    // when will a == b, b
    int[] parent = new int[26];
    for (int i = 0; i < 26; i++) {
      parent[i] = i;
    }
    for (String equation : equations) {
      if (equation.charAt(1) == '=') {
        parent[find(equation.charAt(0) - 'a', parent)] = find(equation.charAt(3) - 'a', parent);
      }
    }
    for (String equation : equations) {
      if (equation.charAt(1) == '!'
          && (find(equation.charAt(0) - 'a', parent) == find(equation.charAt(3) - 'a', parent))) {
        return false;
      }
    }
    return true;
  }

  static int minimumCostForConnections(int N, int[][] connections) {
    int result = 0, numOfNodes = N;
    Arrays.sort(connections, (a, b) -> a[2] - b[2]);
    int[] parent = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }

    for (int[] connection : connections) {
      int start = connection[0] - 1, end = connection[1] - 1;
      int startParent = find(start, parent), endParent = find(end, parent);
      if (startParent != endParent) {
        parent[startParent] = endParent;
        numOfNodes -= 1;
        result += connection[2];
      }
    }

    return numOfNodes == 1 ? result : -1;
  }

  private static int find(int x, int[] parent) {
    if (x != parent[x]) {
      parent[x] = find(parent[x], parent);
    }
    return parent[x];
  }

  static int getDelayTime(int[][] times, int n, int k) {
    int result = 0;
    // [source: {target: dist}]
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    boolean[] visited = new boolean[n + 1];
    Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    pq.add(new int[] {0, k});
    for (int[] time : times) {
      map.putIfAbsent(time[0], new HashMap<>());
      map.get(time[0]).put(time[1], time[2]);
    }

    while (!pq.isEmpty()) {
      int[] element = pq.remove();
      int curDist = element[0], curNode = element[1];
      if (visited[curNode]) {
        continue;
      }
      visited[curNode] = true;
      result = curDist;
      n -= 1;
      if (map.containsKey(curNode)) {
        for (int nextNode : map.get(curNode).keySet()) {
          pq.add(new int[] {curDist + map.get(curNode).get(nextNode), nextNode});
        }
      }
    }

    return n == 0 ? result : -1;
  }

  static boolean isValidPathForStreets(int[][] grid) {

    if (grid == null || grid.length == 0) {
      return false;
    }
    int m = grid.length, n = grid[0].length;
    int[][][] dirs =
        new int[][][] {
          {{0, -1}, {0, 1}},
          {{-1, 0}, {1, 0}},
          {{0, -1}, {1, 0}},
          {{0, 1}, {1, 0}},
          {{-1, 0}, {0, -1}},
          {{-1, 0}, {0, 1}}
        };
    boolean[][] visited = new boolean[m][n];
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[] {0, 0});
    visited[0][0] = true;

    while (!queue.isEmpty()) {
      int[] coordinate = queue.poll();
      int curRow = coordinate[0], curCol = coordinate[1];
      int num = grid[curRow][curCol] - 1;
      if (curRow == m - 1 && curCol == n - 1) {
        return true;
      }
      for (int[] dir : dirs[num]) {
        int nextRow = curRow + dir[0], nextCol = curCol + dir[1];
        if (nextRow < 0
            || nextRow >= m
            || nextCol < 0
            || nextCol >= n
            || visited[nextRow][nextCol]) {
          continue;
        }
        for (int[] backDir : dirs[grid[nextRow][nextCol] - 1]) {
          if (nextRow + backDir[0] == curRow && nextCol + backDir[1] == curCol) {
            visited[nextRow][nextCol] = true;
            queue.add(new int[] {nextRow, nextCol});
          }
        }
      }
    }
    return false;
  }

  public static List<List<String>> accountsMerge(List<List<String>> accounts) {
    // for each email we need to know it's owner
    // for each email we need to konw it's parent
    // after that we need to iterate all emails
    // parent is key
    // email in sorted order TreeSet<>();

    Map<String, String> owner = new HashMap<>();
    Map<String, String> parents = new HashMap<>();
    Map<String, TreeSet<String>> unions = new HashMap<>();
    List<List<String>> result = new ArrayList<>();

    for (List<String> list : accounts) {
      for (int i = 1; i < list.size(); i++) {
        parents.put(list.get(i), list.get(i));
        owner.put(list.get(i), list.get(0));
      }
    }
    for (List<String> list : accounts) {
      String parent = accountFind(list.get(1), parents);
      for (int i = 2; i < list.size(); i++) {
        parents.put(accountFind(list.get(i), parents), parent);
      }
    }
    // finish the find then try to union
    // list.get(1) is the parent of all remaining elements in list
    for (List<String> list : accounts) {
      String parent = accountFind(list.get(1), parents);
      unions.putIfAbsent(parent, new TreeSet<>());
      for (int i = 1; i < list.size(); i++) {
        unions.get(parent).add(list.get(i));
      }
    }

    for (String parent : unions.keySet()) {
      String name = owner.get(parent);
      List<String> emails = new ArrayList<>(unions.get(parent));
      emails.add(0, name);
      result.add(emails);
    }

    return result;
  }

  private static String accountFind(String email, Map<String, String> parents) {
    if (email != parents.get(email)) {
      parents.put(email, accountFind(parents.get(email), parents));
      return parents.get(email);
    }
    return parents.get(email);
  }

  public static int minCostToConnectPoints(int[][] points) {

    int m = points.length;
    int result = 0, island = m;
    int[] parents = new int[m];
    for (int i = 0; i < m; i++) {
      parents[i] = i;
    }
    int[][] dist = new int[m][m];
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> dist[a[0]][a[1]] - dist[b[0]][b[1]]);

    for (int i = 0; i < m; i++) {
      for (int j = 1; j < m; j++) {
        dist[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
        queue.offer(new int[] {i, j});
      }
    }

    while (!queue.isEmpty() && island > 1) {
      int[] node = queue.poll();
      if (pointsUnion(node[0], node[1], parents)) {
        result += dist[node[0]][node[1]];
        island -= 1;
      }
    }
    return result;
  }

  private static boolean pointsUnion(int node1, int node2, int[] parents) {
    int p1 = pointsFind(node1, parents);
    int p2 = pointsFind(node2, parents);
    if (p1 == p2) {
      return false;
    }
    parents[p1] = p2;
    return true;
  }

  private static int pointsFind(int node, int[] parents) {
    int parent = parents[node];
    if (parent == node) {
      return parent;
    }
    parents[node] = pointsFind(parent, parents);
    return parents[node];
  }

  private static void accountsMergeDfsHelper(
      String email, Set<String> visited, TreeSet<String> list, Map<String, Set<String>> graph) {
    list.add(email);
    for (String neighbor : graph.get(email)) {
      if (!visited.contains(neighbor)) {
        visited.add(neighbor);
        accountsMergeDfsHelper(neighbor, visited, list, graph);
      }
    }
  }

  static int sumOddLengthArray(int[] arr) {
    // considering 1,2,3,4,5 of odd sub array
    // 1 will be counted 3 times
    // 2 will be counted 4 times
    // 3 will be counted 5 times
    // 4 will be counted 4 times
    // 5 will be counted 3 times
    // it following the rule of index i = ((n - i) * (i + 1) + 1) / 2
    int result = 0, n = arr.length;
    for (int i = 0; i < n; i++) {
      result += ((n - i) * (i + 1) + 1) / 2 * arr[i];
    }
    return result;
  }

  static int sumEvenLengthArray(int[] arr) {
    int result = 0, n = arr.length;
    for (int i = 0; i < n; i++) {
      result += ((n - i) * (i + 1)) / 2 * arr[i];
    }
    return result;
  }

  static String rankTeams(String[] votes) {
    // for each team, we need to know it's all positions
    Map<Character, int[]> count = new HashMap<>();
    int n = votes[0].length();
    for (String vote : votes) {
      for (int i = 0; i < n; i++) {
        char ch = vote.charAt(i);
        count.putIfAbsent(ch, new int[n]);
        count.get(ch)[i] += 1;
      }
    }
    // then we need to sort all teams
    List<Character> list = new ArrayList<>(count.keySet());
    list.sort(
        (a, b) -> {
          for (int i = 0; i < n; i++) {
            int countA = count.get(a)[i], countB = count.get(b)[i];
            if (countA != countB) {
              return countB - countA;
            }
          }
          return a - b;
        });
    StringBuilder sb = new StringBuilder();
    for (char ch : list) {
      sb.append(ch);
    }
    return sb.toString();
  }

  static int getMaxSubArrayWithPositiveProduct(int[] nums) {
    int count = 0, result = 0, zeroIndex = -1, negativeIndex = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < 0) {
        count += 1;
        if (negativeIndex == -1) {
          negativeIndex = i;
        }
      }
      if (nums[i] == 0) {
        zeroIndex = i;
        negativeIndex = -1;
        count = 0;
      } else {
        // if we have even negative index
        if (count % 2 == 0) {
          result = Math.max(result, i - zeroIndex);
        } else {
          result = Math.max(result, i - negativeIndex);
        }
      }
    }
    return result;
  }

  public static int getMinCostFromOffers(
      List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    Map<String, Integer> cache = new HashMap<>();
    return shoppingOffersDfs(needs, price, special, 0, cache);
  }

  private static int shoppingOffersDfs(
      List<Integer> needs,
      List<Integer> price,
      List<List<Integer>> special,
      int index,
      Map<String, Integer> cache) {
    // directly buying it
    int minPrice = getDirectPrice(needs, price);
    String key = needs.toString();
    if (cache.containsKey(key)) {
      return cache.get(key);
    }
    for (int i = index; i < special.size(); i++) {
      List<Integer> offer = special.get(i);
      List<Integer> curNeed = new ArrayList<>();
      for (int j = 0; j < offer.size() - 1; j++) {
        if (offer.get(j) > needs.get(j)) {
          curNeed.clear();
          break;
        }
        curNeed.add(j, needs.get(j) - offer.get(j));
      }
      if (!curNeed.isEmpty()) {
        minPrice =
            Math.min(
                minPrice,
                offer.get(offer.size() - 1)
                    + shoppingOffersDfs(curNeed, price, special, index, cache));
      }
    }
    cache.put(key, minPrice);
    return minPrice;
  }

  private static int getDirectPrice(List<Integer> needs, List<Integer> price) {
    int result = 0;
    for (int i = 0; i < needs.size(); i++) {
      result += needs.get(i) * price.get(i);
    }
    return result;
  }

  static int[][] drawColorOnBorder(int[][] grid, int r0, int c0, int color) {
    // first come to my mind is bfs
    int m = grid.length, n = grid[0].length;
    boolean[][] visited = new boolean[m][n];
    Queue<int[]> queue = new LinkedList<>();
    int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    queue.add(new int[] {r0, c0});
    List<int[]> border = new ArrayList<>();
    while (!queue.isEmpty()) {
      int[] node = queue.poll();
      int x = node[0], y = node[1];
      for (int[] direction : directions) {
        int nextX = x + direction[0];
        int nextY = y + direction[1];
        if (nextX >= 0
            && nextX < m
            && nextY >= 0
            && nextY < n
            && grid[nextX][nextY] == grid[x][y]) {
          if (!visited[nextX][nextY]) {
            visited[nextX][nextY] = true;
            queue.add(new int[] {nextX, nextY});
          }
        } else {
          border.add(new int[] {x, y});
        }
      }
    }
    for (int[] element : border) {
      grid[element[0]][element[1]] = color;
    }
    return grid;
  }

  static int[] numsSameDiffBfs(int n, int k) {
    Queue<Integer> queue = new ArrayDeque<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    while (n > 1) {
      int size = queue.size();
      for (int i = size; i > 0; i--) {
        int num = queue.poll();
        if (num > 0) {
          int digit1 = num % 10 - k, digit2 = num % 10 + k;
          if (digit1 >= 0) {
            queue.offer(num * 10 + digit1);
          }
          if (digit2 < 10 && digit1 != digit2) {
            queue.offer(num * 10 + digit2);
          }
        }
      }
      n -= 1;
    }
    return queue.stream().mapToInt(i -> i).toArray();
  }

  static int[] numsSameConsecDiffDfs(int n, int k) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      numsSameConsecDiffDfsHelper(n, k, i, result);
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  private static void numsSameConsecDiffDfsHelper(int n, int k, int num, List<Integer> result) {
    if (n == 1) {
      result.add(num);
    } else if (num > 0) {
      int digit1 = num % 10 - k, digit2 = num % 10 + k;
      if (digit1 >= 0) {
        numsSameConsecDiffDfsHelper(n - 1, k, num * 10 + digit1, result);
      }
      if (digit2 < 10 && digit2 != digit1) {
        numsSameConsecDiffDfsHelper(n - 1, k, num * 10 + digit2, result);
      }
    }
  }

  static int numSubMatrix(int[][] mat) {
    if (mat == null || mat.length == 0 || mat[0].length == 0) {
      return -1;
    }
    int m = mat.length, n = mat[0].length, result = 0;
    int[] height = new int[n];
    for (int[] ints : mat) {
      for (int j = 0; j < n; j++) {
        height[j] = ints[j] == 0 ? 0 : height[j] + 1;
        int min = height[j];
        for (int k = j; k >= 0 && min >= 0; k--) {
          min = Math.min(height[k], min);
          result += min;
        }
      }
    }
    return result;
  }

  static boolean canSplitArrayIntoConsecutiveSequences(int[] nums) {
    if (nums.length < 3) {
      return false;
    }
    Map<Integer, Integer> freq = new HashMap<>();
    Map<Integer, Integer> sequence = new HashMap<>();
    for (int num : nums) {
      freq.put(num, freq.getOrDefault(num, 0) + 1);
    }
    for (int num : nums) {
      if (freq.get(num) == 0) {
        continue;
      } else if (sequence.getOrDefault(num, 0) > 0) {
        sequence.put(num, sequence.get(num) - 1);
        sequence.put(num + 1, sequence.getOrDefault(num + 1, 0) + 1);
      } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
        freq.put(num + 1, freq.get(num + 1) - 1);
        freq.put(num + 2, freq.get(num + 2) - 1);
        sequence.put(num + 3, sequence.getOrDefault(num + 3, 0) + 1);
      } else {
        return false;
      }

      freq.put(num, freq.get(num) - 1);
    }
    return true;
  }

  static List<String> buildStackOperationArray(int[] target, int n) {
    List<String> result = new ArrayList<>();

    if (target == null || target.length == 0 || n <= 0) {
      return result;
    }
    int cur = 1;
    for (int i = 0; i < target.length; i++) {
      result.add("Push");
      if (cur != target[i]) {
        result.add("Pop");
        i -= 1;
      }
      cur += 1;
    }
    return result;
  }

  static int[] smallestSubSequence(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k <= 0) {
      return new int[] {};
    }
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[k];
    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty()
          && nums[i] < nums[stack.peek()]
          && stack.size() + nums.length - i > k) {
        stack.pop();
      }
      if (stack.size() < k) {
        stack.add(i);
      }
    }
    for (int i = k - 1; i >= 0; i--) {
      result[i] = nums[stack.pop()];
    }
    return result;
  }

  public List<List<String>> accountsMergeDfs(List<List<String>> accounts) {
    // build graph use dfs
    // use visited to store visited node
    // need to store mapping from email to name
    List<List<String>> result = new ArrayList<>();
    Map<String, Set<String>> graph = new HashMap<>();
    Map<String, String> emailToName = new HashMap<>();
    Set<String> visited = new HashSet<>();

    for (List<String> list : accounts) {
      for (int i = 1; i < list.size(); i++) {
        emailToName.put(list.get(i), list.get(0));
        graph.putIfAbsent(list.get(i), new HashSet<>());
        if (i > 1) {
          graph.get(list.get(i)).add(list.get(i - 1));
          graph.get(list.get(i - 1)).add(list.get(i));
        }
      }
    }

    // iterate email to do dfs

    for (String email : emailToName.keySet()) {
      TreeSet<String> sortedList = new TreeSet<>();
      if (!visited.contains(email)) {
        visited.add(email);
        accountsMergeDfsHelper(email, visited, sortedList, graph);
        List<String> list = new ArrayList<>(sortedList);
        list.add(0, emailToName.get(email));
        result.add(list);
      }
    }
    return result;
  }

  public List<List<String>> accountsMergeBfs(List<List<String>> accounts) {
    // build graph use bfs
    // use visited to store visited node
    // need to store mapping from email to name
    List<List<String>> result = new ArrayList<>();
    Map<String, Set<String>> graph = new HashMap<>();
    Map<String, String> emailToName = new HashMap<>();
    Set<String> visited = new HashSet<>();

    for (List<String> list : accounts) {
      for (int i = 1; i < list.size(); i++) {
        emailToName.put(list.get(i), list.get(0));
        graph.putIfAbsent(list.get(i), new HashSet<>());
        if (i > 1) {
          graph.get(list.get(i)).add(list.get(i - 1));
          graph.get(list.get(i - 1)).add(list.get(i));
        }
      }
    }

    // iterate email to do bfs
    Queue<String> queue = new LinkedList<>();
    for (String email : emailToName.keySet()) {
      TreeSet<String> sortedList = new TreeSet<>();
      if (!visited.contains(email)) {
        queue.add(email);
        while (!queue.isEmpty()) {
          String node = queue.poll();
          sortedList.add(node);
          for (String neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
              queue.add(neighbor);
              visited.add(neighbor);
            }
          }
        }
        List<String> list = new ArrayList<>(sortedList);
        list.add(0, emailToName.get(email));
        result.add(list);
      }
    }
    return result;
  }
}
