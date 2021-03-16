package com.monpro.generics.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeUtilsTest {

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

  @Test
  void deleteNodesTest() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);

    root.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);
    List<TreeNode> list = TreeUtils.deleteNodes(root, new int[] {3, 5});
    assertEquals(list.size(), 3);
    assertEquals(list.get(0).val, 1);
    assertEquals(list.get(1).val, 6);
    assertEquals(list.get(2).val, 7);
  }

  @Test
  void treeToSortedDoublyListTest() {
    TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right = new TreeNode(5);

    TreeNode sortedRoot = TreeUtils.treeToSortedDoublyListTest(root);
    assertEquals(sortedRoot.val, 1);
    assertEquals(sortedRoot.left.val, 5);
    assertEquals(sortedRoot.right.val, 2);
  }

  @Test
  void flipTreeUpsideDownTest() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);

    TreeNode newRoot = TreeUtils.flipTreeUpsideDown(root);
    assertEquals(newRoot.val, 2);
    assertEquals(newRoot.left.val, 3);
    assertEquals(newRoot.right.val, 1);
  }

  @Test
  void deepestSubTreeTest() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.left.left.left = new TreeNode(9);
    root.left.left.right = new TreeNode(10);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);

    assertEquals(TreeUtils.deepestSubTree(root).val, 4);
  }

  @Test
  void nodeWithNoSiblingTest() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.left.left.left = new TreeNode(9);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    List<Integer> result = TreeUtils.nodeWithNoSibling(root);
    List<Integer> list = new ArrayList<>(Arrays.asList(6, 9));
    assertTrue(
        result.size() == list.size() && result.containsAll(list) && list.containsAll(result));
  }
}
