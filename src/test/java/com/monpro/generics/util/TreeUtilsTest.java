package com.monpro.generics.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeUtilsTest {

  @Test
  void maximumAverageSubValueTest() {
    TreeNode treeNode = new TreeNode();
    treeNode.val = 10;
    treeNode.left = new TreeNode(15);
    treeNode.right = new TreeNode(13);
    assertEquals(TreeUtils.maximumAverageSubValue(treeNode), 15);
    treeNode.left.left = new TreeNode(0);
    treeNode.left.right = new TreeNode(1);
    assertEquals(TreeUtils.maximumAverageSubValue(treeNode), 13);
  }

  @Test
  void buildExpressionTreeTest() {
    assertEquals(
        TreeUtils.buildExpressionTree(new String[] {"2", "*", "6", "-", "5"}).toString(),
        "symbol='-', left=symbol='*', left=symbol='2', left=null, right=null}, right=symbol='6', left=null, right=null}}, right=symbol='5', left=null, right=null}}");
  }

  @Test
  void maxDiffBetweenAncestorsAndNodeHelperTest() {
    TreeNode root = new TreeNode(8);
    root.left = new TreeNode(3);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(2);
    root.right = new TreeNode(14);

    assertEquals(TreeUtils.maxDiffBetweenAncestorsAndNode(root), 7);
  }
}
