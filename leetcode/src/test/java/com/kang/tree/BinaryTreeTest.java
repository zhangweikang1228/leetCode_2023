package com.kang.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    BinaryTree binaryTree = new BinaryTree();

    @Test
    public void sumOfLeftLeaves() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);


        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        binaryTree.sumOfLeftLeaves(node1);



    }

    @Test
    public void haspathsum() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);


        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node2.left = node7;
        node2.right = node8;
        node8.left = node9;

        binaryTree.haspathsum(node1,20);

    }

    @Test
    public void buildTree() {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        binaryTree.buildTree(inorder,postorder);
    }
}