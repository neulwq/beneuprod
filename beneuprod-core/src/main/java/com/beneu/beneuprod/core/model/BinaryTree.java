package com.beneu.beneuprod.core.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/1/19 22:21
 */
public class BinaryTree<T extends Comparable<T>> {

    /** 根节点 */
    private BinaryTreeNode<T> root;

    /** 构造函数 */
    public BinaryTree() {}

    /**
     * 返回树的高度
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 返回树节点的深度
     *
     * @return
     */
    protected int height(BinaryTreeNode<T> node) {
        if (node == null) {
            //叶子节点树高为0
            return -1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 广度优先遍历
     *
     * @return
     */
    public List<BinaryTreeNode<T>> breadthVisit() {
        List<BinaryTreeNode<T>> values = new ArrayList<>();
        Queue<BinaryTreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        for (BinaryTreeNode<T> visitNode = queue.poll(); visitNode != null; visitNode = queue.poll()) {
            values.add(visitNode);
            if (visitNode.left != null) {
                queue.offer(visitNode.left);
            }
            if (visitNode.right != null) {
                queue.offer(visitNode.right);
            }
        }
        return values;
    }

    /**
     * 添加数组
     *
     * @param array
     */
    public void add(T[] array) {
        for (T value : array) {
            add(value);
        }
    }

    /**
     * 添加
     *
     * @param value
     */
    public void add(T value) {
        root = add(root, value);
    }


    /**
     * 添加元素
     *
     * @param value
     * @return
     */
    protected BinaryTreeNode<T> add(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return new BinaryTreeNode<>(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = add(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = add(node.right, value);
        }
        return node;
    }


    /**
     * 前序遍历
     * @return
     */
    public List<BinaryTreeNode<T>> preVisit() {
        List<BinaryTreeNode<T>> list = new ArrayList<>();
        preVisitTree(list, root);
        return list;
    }

    /**
     * 中序遍历
     * @return
     */
    public List<BinaryTreeNode<T>> midVisit() {
        List<BinaryTreeNode<T>> list = new ArrayList<>();
        midVisitTree(list, root);
        return list;
    }

    /**
     * 后序遍历
     * @return
     */
    public List<BinaryTreeNode<T>> postVisit() {
        List<BinaryTreeNode<T>> list = new ArrayList<>();
        postVisitTree(list, root);
        return list;
    }

    /**
     * 前序递归
     *
     * @param list
     * @return
     */
    protected void preVisitTree(List<BinaryTreeNode<T>> list, BinaryTreeNode node) {
        list.add(node);
        if (node.left != null) {
            preVisitTree(list, node.left);
        }
        if (node.right != null) {
            preVisitTree(list, node.right);
        }
    }

    /**
     * 中序递归
     *
     * @param list
     * @return
     */
    protected void midVisitTree(List<BinaryTreeNode<T>> list, BinaryTreeNode node) {
        if (node.left != null) {
            midVisitTree(list, node.left);
        }
        list.add(node);
        if (node.right != null) {
            midVisitTree(list, node.right);
        }
    }

    /**
     * 后序递归
     *
     * @param list
     * @return
     */
    protected void postVisitTree(List<BinaryTreeNode<T>> list, BinaryTreeNode node) {
        if (node.left != null) {
            postVisitTree(list, node.left);
        }
        if (node.right != null) {
            postVisitTree(list, node.right);
        }
        list.add(node);
    }

}
