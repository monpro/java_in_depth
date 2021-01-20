package com.monpro.generics.util;

import java.util.Stack;

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
}
