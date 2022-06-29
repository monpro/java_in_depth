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

}
