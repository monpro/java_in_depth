package com.monpro.generics.util;

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
  String symbol;
  ExpressionTreeNode left, right;

  public ExpressionTreeNode(String symbol) {
    this.symbol = symbol;
    this.left = this.right = null;
  }

  @Override
  public String toString() {
    return "symbol='" + symbol + '\'' + ", left=" + left + ", right=" + right + '}';
  }
}

public class TreeUtils {
  private TreeUtils() {}

  public static double maximumAverageSubValue(TreeNode root) {
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

  public static ExpressionTreeNode buildExpressionTree(String[] expression) {
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

  public static int maxDiffBetweenAncestorsAndNode(TreeNode root) {
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

  public static List<TreeNode> deleteNodes(TreeNode root, int[] deleteNodeValues) {
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

  public static TreeNode treeToSortedDoublyListTest(TreeNode root) {
    // then link together
    if(root == null) {
      return null;
    }

    TreeNode leftNode = treeToSortedDoublyListTest(root.left);
    TreeNode rightNode = treeToSortedDoublyListTest(root.right);

    root.left = root;
    root.right = root;

    return treeToSortedDoublyListTestMerge(treeToSortedDoublyListTestMerge(leftNode, root), rightNode);
  }

  private static TreeNode treeToSortedDoublyListTestMerge(TreeNode leftNode, TreeNode rightNode) {
    if(leftNode == null) {
      return rightNode;
    }
    if(rightNode == null) {
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

    public static TreeNode flipTreeUpsideDown(TreeNode root) {
        if(root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = flipTreeUpsideDown(root.left);
        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;

        return newRoot;
    }
}
