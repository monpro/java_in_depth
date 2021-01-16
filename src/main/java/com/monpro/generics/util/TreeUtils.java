package com.monpro.generics.util;

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
}
