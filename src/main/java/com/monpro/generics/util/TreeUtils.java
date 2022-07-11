package com.monpro.generics.util;

import javafx.util.Pair;

import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TreeNode treeNode = (TreeNode) o;
    return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(val, left, right);
  }
}

class AverageNode {
  double val;
  int count;
  double maxAverageVal;

  AverageNode(double val, int count, double maxVal) {
    this.val = val;
    this.count = count;
    this.maxAverageVal = maxVal;
  }
}

class ExpressionTreeNode {
  private final String symbol;
  ExpressionTreeNode left, right;

  ExpressionTreeNode(String symbol) {
    this.symbol = symbol;
    this.left = this.right = null;
  }

  @Override
  public String toString() {
    return "symbol='" + symbol + '\'' + ", left=" + left + ", right=" + right + '}';
  }
}

class RowColNode {
  int row;
  int col;
  TreeNode node;

  public RowColNode(int row, int col, TreeNode node) {
    this.row = row;
    this.col = col;
    this.node = node;
  }
}



class TreeUtils {
  private TreeUtils() {}

  static double maximumAverageSubValue(TreeNode root) {
    return maximumAverageSubValueHelper(root).maxAverageVal;
  }

  private static AverageNode maximumAverageSubValueHelper(TreeNode root) {
    if (root == null) {
      return new AverageNode(0, 0, 0);
    }

    AverageNode left = maximumAverageSubValueHelper(root.left);
    AverageNode right = maximumAverageSubValueHelper(root.right);
    double sumVal = root.val + left.val + right.val;
    int sumCount = 1 + left.count + right.count;
    double result = Math.max(Math.max(left.maxAverageVal, right.maxAverageVal), sumVal / sumCount);
    return new AverageNode(sumVal, sumCount, result);
  }

  static ExpressionTreeNode buildExpressionTree(String[] expression) {
    if (expression == null || expression.length == 0) {
      return null;
    }
    Stack<String> stack = new Stack<>();
    Stack<ExpressionTreeNode> expressionTreeStack = new Stack<>();

    for (String str : expression) {
      if (str.equals("(")) {
        stack.add(str);
      } else if (str.equals(")")) {
        while (!stack.isEmpty() && !stack.peek().equals("(")) {
          expressionTreeStack.add(calculateExpression(stack.pop(), expressionTreeStack));
        }
        stack.pop();
      } else if (Character.isDigit(str.charAt(0))) {
        expressionTreeStack.add(new ExpressionTreeNode(str));
      } else {
        while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(str)) {
          expressionTreeStack.add(calculateExpression(stack.pop(), expressionTreeStack));
        }
        stack.add(str);
      }
    }

    while (!stack.isEmpty()) {
      expressionTreeStack.add(calculateExpression(stack.pop(), expressionTreeStack));
    }
    return expressionTreeStack.isEmpty() ? null : expressionTreeStack.pop();
  }

  private static int getPriority(String symbol) {
    if (symbol.equals("*") || symbol.equals("/")) {
      return 3;
    } else if (symbol.equals("+") || symbol.equals("-")) {
      return 2;
    } else {
      return 1;
    }
  }

  private static ExpressionTreeNode calculateExpression(
      String symbol, Stack<ExpressionTreeNode> expressionTreeStack) {
    ExpressionTreeNode node = new ExpressionTreeNode(symbol);
    node.right = expressionTreeStack.pop();
    node.left = expressionTreeStack.pop();
    return node;
  }

  static int maxDiffBetweenAncestorsAndNode(TreeNode root) {
    return maxDiffBetweenAncestorsAndNodeHelper(root, root.val, root.val);
  }

  private static int maxDiffBetweenAncestorsAndNodeHelper(
      TreeNode root, int maxValue, int minValue) {
    if (root == null) {
      return Math.abs(maxValue - minValue);
    }
    maxValue = Math.max(root.val, maxValue);
    minValue = Math.min(root.val, minValue);

    return Math.max(
        maxDiffBetweenAncestorsAndNodeHelper(root.left, maxValue, minValue),
        maxDiffBetweenAncestorsAndNodeHelper(root.right, maxValue, minValue));
  }

  static List<TreeNode> deleteNodes(TreeNode root, int[] deleteNodeValues) {
    List<TreeNode> result = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    for (int node : deleteNodeValues) {
      set.add(node);
    }
    if (!set.contains(root.val)) {
      result.add(root);
    }
    deleteNodesHelper(root, set, result);
    return result;
  }

  private static TreeNode deleteNodesHelper(
      TreeNode root, Set<Integer> set, List<TreeNode> result) {
    if (root == null) {
      return null;
    }

    root.left = deleteNodesHelper(root.left, set, result);
    root.right = deleteNodesHelper(root.right, set, result);

    if (set.contains(root.val)) {
      if (root.left != null) {
        result.add(root.left);
      }
      if (root.right != null) {
        result.add(root.right);
      }
      return null;
    }
    return root;
  }

  static TreeNode treeToSortedDoublyListTest(TreeNode root) {
    // then link together
    if (root == null) {
      return null;
    }

    TreeNode leftNode = treeToSortedDoublyListTest(root.left);
    TreeNode rightNode = treeToSortedDoublyListTest(root.right);

    root.left = root;
    root.right = root;

    return treeToSortedDoublyListTestMerge(
        treeToSortedDoublyListTestMerge(leftNode, root), rightNode);
  }

  private static TreeNode treeToSortedDoublyListTestMerge(TreeNode leftNode, TreeNode rightNode) {
    if (leftNode == null) {
      return rightNode;
    }
    if (rightNode == null) {
      return leftNode;
    }

    TreeNode leftTail = leftNode.left;
    TreeNode rightTail = rightNode.left;

    leftTail.right = rightNode;
    rightNode.left = leftTail;

    rightTail.right = leftNode;
    leftNode.left = rightTail;

    return leftNode;
  }

  static TreeNode flipTreeUpsideDown(TreeNode root) {
    if (root == null || root.left == null) {
      return root;
    }

    TreeNode newRoot = flipTreeUpsideDown(root.left);
    root.left.left = root.right;
    root.left.right = root;

    root.left = null;
    root.right = null;

    return newRoot;
  }

  static TreeNode deepestSubTree(TreeNode root) {
    return deepestSubTreeHelper(root).getValue();
  }

  private static Pair<Integer, TreeNode> deepestSubTreeHelper(TreeNode root) {
    if (root == null) {
      return new Pair<>(0, null);
    }

    Pair<Integer, TreeNode> leftPair = deepestSubTreeHelper(root.left);
    Pair<Integer, TreeNode> rightPair = deepestSubTreeHelper(root.right);

    int leftHeight = leftPair.getKey(), rightHeight = rightPair.getKey();
    if (leftHeight == rightHeight) {
      return new Pair<>(leftHeight + 1, root);
    } else if (leftHeight > rightHeight) {
      return new Pair<>(leftHeight + 1, leftPair.getValue());
    } else {
      return new Pair<>(rightHeight + 1, rightPair.getValue());
    }
  }

  static List<Integer> nodeWithNoSibling(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    lonelyNodesHelper(root, result);
    return result;
  }

  private static void lonelyNodesHelper(TreeNode root, List<Integer> result) {
    if (root.left == null && root.right == null) {
      return;
    }
    if (root.left != null && root.right == null) {
      result.add(root.left.val);
      lonelyNodesHelper(root.left, result);
    } else if (root.right != null && root.left == null) {
      result.add(root.right.val);
      lonelyNodesHelper(root.right, result);
    } else {
      lonelyNodesHelper(root.left, result);
      lonelyNodesHelper(root.right, result);
    }
    }

  /**
   * descriptions[i] = [parentI, childI, isLeftI] indicates that parentI is the parent of childI in
   * a binary tree of unique values. Furthermore, If isLeftI == 1, then childI is the left child of
   * parentI. If isLeftI == 0, then childI is the right child of parentI.
   */
  public static TreeNode createBinaryTree(int[][] descriptions) {

    // we need to have the mapping value -> node
    Map<Integer, TreeNode> valueToNode = new HashMap<>();
    Set<Integer> children = new HashSet<>();

    for (int[] description : descriptions) {
      int parent = description[0], child = description[1], isLeft = description[2];

      TreeNode parentNode = valueToNode.getOrDefault(parent, new TreeNode(parent));

      TreeNode childNode = valueToNode.getOrDefault(child, new TreeNode(child));
      if (isLeft == 1) {
        parentNode.left = childNode;
      } else {
        parentNode.right = childNode;
      }
      valueToNode.put(parent, parentNode);
      valueToNode.put(child, childNode);
      children.add(child);
    }
    TreeNode root = null;
    for (int[] description : descriptions) {
      if (!children.contains(description[0])) {
        root = valueToNode.get(description[0]);
        break;
      }
    }
    return root;
  }

  static int nodeCount = 0;
  public static int averageOfSubtree(TreeNode root) {
    averageHelper(root);
    return nodeCount;
  }

  private static Pair<Integer, Integer> averageHelper(TreeNode root) {
    if (root == null) {
      return new Pair<>(0, 0);
    }

    Pair<Integer, Integer> left = averageHelper(root.left);
    Pair<Integer, Integer> right = averageHelper(root.right);
    int sum = root.val + left.getKey() + right.getKey();
    int count = 1 + left.getValue() + right.getValue();
    if (sum / count == root.val) {
      nodeCount += 1;
    }
    return new Pair<>(sum, count);
  }

  private static final Map<TreeNode, Integer> countMemory = new HashMap<>();
  public static int equalToDescendants(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = sumNodes(root.left);
    int right = sumNodes(root.right);
    int count = 0;
    if (left + right == root.val) {
      count = 1;
    }
    return count + equalToDescendants(root.left) + equalToDescendants(root.right);
  }

  private static int sumNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (countMemory.containsKey(root)) {
      return countMemory.get(root);
    }
    int sum = root.val + sumNodes(root.left) + sumNodes(root.right);
    countMemory.put(root, sum);
    return sum;
  }

  public static TreeNode str2tree(String s) {
    // if meet ')', we need to pop up the element, then the stack top is parent
    // if meet '-', we need to adjust the sign
    // if meet '(', we simply cur++;
    // if meet number, multiply by sign, then add into the slack
    Stack<TreeNode> stack = new Stack<>();
    int sign = 1, cur = 0;
    TreeNode parent = null, child = null;
    while (cur < s.length()) {
      char curChar = s.charAt(cur);
      if (curChar == ')') {
        child = stack.pop();
        parent = stack.peek();
        if (parent.left == null) {
          parent.left = child;
        } else {
          parent.right = child;
        }
        cur += 1;
      }
      else if (curChar == '(') {
        cur += 1;
      }
      else if (curChar == '-') {
        sign = -1;
        cur += 1;
      }
      else {
        int num = 0;
        while (cur < s.length() && s.charAt(cur) >= '0' && s.charAt(cur)  <= '9') {
          num = num * 10 + s.charAt(cur) - '0';
          cur += 1;
        }
        stack.push(new TreeNode(sign * num));
        sign = 1;
      }
    }
    if (!stack.isEmpty()) {
      return stack.peek();
    }
    return parent;
  }

  public static int countHighestScoreNodes(int[] parents) {
    List<List<Integer>> parentsToChild = new ArrayList<>();
    for (int i = 0; i < parents.length; i++) {
      parentsToChild.add(new ArrayList<>());
    }
    long[] count = new long[parents.length];
    for (int i = 1; i< parents.length; i++) {
      parentsToChild.get(parents[i]).add(i);
    }
    getSum(parentsToChild, count, 0);
    long maxCount = Arrays.stream(count).max().getAsLong();
    return (int) Arrays.stream(count).filter(value -> value == maxCount).count();
  }

  private static long getSum(List<List<Integer>> parentsToChild, long[] count, int index) {
    long prod = 1, sum = 1;
    for(int child : parentsToChild.get(index)) {
      long childSum = getSum(parentsToChild, count, child);
      prod *= childSum;
      sum += childSum;
    }
    count[index] = prod * Math.max(1, parentsToChild.size() - sum);
    return sum;
  }

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    Queue<RowColNode> queue = new LinkedList<>();
    TreeMap<Integer, List<RowColNode>> colToNodes = new TreeMap<>();
    queue.add(new RowColNode(0, 0, root));
    while (!queue.isEmpty()) {
      RowColNode rowColNode = queue.poll();
      List<RowColNode> nodes = colToNodes.getOrDefault(rowColNode.col, new ArrayList<>());
      nodes.add(rowColNode);
      colToNodes.put(rowColNode.col, nodes);

      if (rowColNode.node.left != null) {
        queue.add(new RowColNode(rowColNode.row + 1, rowColNode.col - 1, rowColNode.node.left));
      }
      if (rowColNode.node.right != null) {
        queue.add(new RowColNode(rowColNode.row + 1, rowColNode.col + 1, rowColNode.node.right));
      }
    }
    colToNodes.forEach(
      (col, nodes) -> { nodes.sort((node1, node2) -> {
        if (node1.row < node2.row) return -1;
        else if (node1.row > node2.row) return 1;
        else return node1.node.val - node2.node.val;
      });
      List<Integer> curColNodes = new ArrayList<>();
      for (RowColNode rowColNode : nodes) {
        curColNodes.add(rowColNode.node.val);
      }
      results.add(curColNodes);
    });
    return results;
  }

  public static List<List<String>> printTree(TreeNode root) {
    List<List<String>> result = new ArrayList<>();
    int height = getHeight(root);
    int row = height, col = (int)(Math.pow(2, height) - 1);
    for(int i = 0; i < row; i++) {
      List<String> temp = new ArrayList<>();
      for(int j = 0; j < col; j++) {
        temp.add("");
      }
      result.add(temp);
    }
    buildTree(result, root, 0, col - 1, row, 0);
    return result;
  }

  private static void buildTree(List<List<String>> result, TreeNode root, int left, int right, int height, int level) {
    if (height == level || root == null) return;

    int mid = left + (right - left) / 2;
    result.get(level).set(mid, String.valueOf(root.val));
    buildTree(result, root.left, left, mid - 1, height, level + 1);
    buildTree(result, root.right, mid + 1, right, height, level + 1);
  }

   private static int getHeight(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(getHeight(root.left), getHeight(root.right));
  }
}
