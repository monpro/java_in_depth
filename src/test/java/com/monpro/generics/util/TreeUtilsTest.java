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
}
