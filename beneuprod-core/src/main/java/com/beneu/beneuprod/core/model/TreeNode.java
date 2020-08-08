package com.beneu.beneuprod.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <Description>: 树节点定义
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/6 22:40
 */
@Getter
@Setter
public class TreeNode<T extends Comparable<T>> {

    /** 节点的值 **/
    private T value;

    /** 左子树 */
    private TreeNode<T> left;

    /** 右子树 */
    private TreeNode<T> right;

    /** 构造函数 */
    public TreeNode(T value) {
        this.value = value;
    }

    /**
     * 前序遍历
     * @return
     */
    public List<T> preVisit() {
        return preVisitTree(this);
    }

    /**
     * 中序遍历
     * @return
     */
    public List<T> midVisit() {
        return midVisitTree(this);
    }

    /**
     * 后序遍历
     * @return
     */
    public List<T> postVisit() {
        return postVisitTree(this);
    }

    /**
     * 广度优先遍历
     *
     * @return
     */
    public List<T> breadthVisit() {
        List<T> values = new ArrayList<>();
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(this);

        for (TreeNode<T> visitNode = queue.poll(); visitNode != null; visitNode = queue.poll()) {
            values.add(visitNode.value);
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
     * 广度优先镜像操作
     */
    public void breadthMirror() {
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(this);

        for (TreeNode<T> visitNode = queue.poll(); visitNode != null; visitNode = queue.poll()) {
            if (visitNode.left != null) {
                queue.offer(visitNode.left);
            }
            if (visitNode.right != null) {
                queue.offer(visitNode.right);
            }
            TreeNode<T> leftNode = visitNode.left;
            //左右反转
            visitNode.setLeft(visitNode.right);
            visitNode.setRight(leftNode);
        }
    }

    /**
     * 添加元素
     *
     * @param value
     * @return
     */
    public TreeNode<T> add(T value) {
        if (value.compareTo(this.value) == 0) {
            return this;
        }
        if (value.compareTo(this.value) < 0) {
            if (this.left == null) {
                this.left = new TreeNode<>(value);
                return this.left;
            }
            return this.left.add(value);
        }

        if (this.right == null) {
            this.right = new TreeNode<>(value);
            return this.right;
        }
        return this.right.add(value);
    }

    /**
     * 递归镜像
     */
    public void noBreadthMirror() {
        recursiveMirror(this);
    }

    /**
     * 递归镜像
     *
     * @param tree
     */
    protected void recursiveMirror(TreeNode<T> tree) {
        if (tree.left != null) {
            recursiveMirror(tree.left);
        }

        if (tree.right != null) {
            recursiveMirror(tree.right);
        }

        TreeNode<T> leftNode = tree.left;
        //左右反转
        tree.setLeft(tree.right);
        tree.setRight(leftNode);
    }

    /**
     * 前序递归
     *
     * @param tree
     * @return
     */
    protected List<T> preVisitTree(TreeNode<T> tree) {
        List<T> values = new ArrayList<>();
        values.add(tree.getValue());
        if (tree.left != null) {
            values.addAll(preVisitTree(tree.left));
        }
        if (tree.right != null) {
            values.addAll(preVisitTree(tree.right));
        }
        return values;
    }

    /**
     * 中序递归
     *
     * @param tree
     * @return
     */
    protected List<T> midVisitTree(TreeNode<T> tree) {
        List<T> values = new ArrayList<>();
        if (tree.left != null) {
            values.addAll(midVisitTree(tree.left));
        }
        values.add(tree.getValue());
        if (tree.right != null) {
            values.addAll(midVisitTree(tree.right));
        }
        return values;
    }

    /**
     * 后序递归
     *
     * @param tree
     * @return
     */
    protected List<T> postVisitTree(TreeNode<T> tree) {
        List<T> values = new ArrayList<>();
        if (tree.left != null) {
            values.addAll(postVisitTree(tree.left));
        }
        if (tree.right != null) {
            values.addAll(postVisitTree(tree.right));
        }
        values.add(tree.getValue());
        return values;
    }
}
