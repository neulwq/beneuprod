package com.beneu.beneuprod.core.model;

import com.alibaba.fastjson.JSON;
import com.beneu.common.util.log.MessageFormatUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

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
@Setter
@Getter
public class TreeNode<T extends Comparable<T>> {

    /** 节点的值 **/
    protected T value;

    /** 左子树 */
    protected transient TreeNode<T> left;

    /** 右子树 */
    protected transient TreeNode<T> right;

    /** 构造函数 */
    public TreeNode() {}

    /** 构造函数 */
    public TreeNode(T value) {
        this.value = value;
    }

    /**
     * 可视化
     *
     * @return
     */
    public String view() {
        //未实现
        return null;
    }

    /**
     * 按层级遍历
     *
     * @return
     */
    public List<List<TreeNode<T>>> breadthLevelVisitWithFill() {
        TreeNode<T> tree = deepClone();
        List<List<TreeNode<T>>> wideList = new ArrayList<>();
        for (int i = 0; i < maxDepth(); i++) {
            wideList.add(new ArrayList<>());
        }

        int maxLevel = tree.maxDepth() - 1;
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(tree);

        for (TreeNode<T> node = queue.poll(); node != null; node = queue.poll()) {
            wideList.get(node.currentLevel()).add(node);
            if (node.left == null && node.currentLevel() < maxLevel) {
                node.add2Left(null);
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right == null && node.currentLevel() < maxLevel) {
                node.add2Right(null);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return wideList;
    }


    /**
     * 按层级遍历
     *
     * @return
     */
    public List<List<TreeNode<T>>> breadthLevelVisit() {
        List<List<TreeNode<T>>> wideList = new ArrayList<>();
        for (int i = 0; i < maxDepth(); i++) {
            wideList.add(new ArrayList<>());
        }

        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(this);

        for (TreeNode<T> node = queue.poll(); node != null; node = queue.poll()) {
            wideList.get(node.currentLevel()).add(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return wideList;
    }

    /**
     * 当前的层级
     *
     * @return
     */
    protected int currentLevel() {
        return 0;
    }

    /**
     * 深拷贝
     *
     * @return
     */
    public TreeNode<T> deepClone() {
        TreeNode<T> treeNode = shallowClone();
        if (this.left != null) {
            treeNode.left = this.left.deepClone();
        }
        if (this.right != null) {
            treeNode.right = this.right.deepClone();
        }
        return treeNode;
    }

    /**
     * 浅拷贝
     *
     * @return
     */
    protected TreeNode<T> shallowClone() {
        return new TreeNode<>(value);
    }

    /**
     * 返回树的深度
     *
     * @return
     */
    public int maxDepth() {
        return Math.max(left == null ? 0 : left.maxDepth(), right == null ? 0 : right.maxDepth()) + 1;
    }

    /**
     * 广度优先遍历
     *
     * @return
     */
    public List<TreeNode<T>> breadthVisit() {
        List<TreeNode<T>> values = new ArrayList<>();
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(this);

        for (TreeNode<T> visitNode = queue.poll(); visitNode != null; visitNode = queue.poll()) {
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
     * 添加元素
     *
     * @param value
     * @return
     */
    public TreeNode<T> add(T value) {
        if (value.compareTo(this.value) == 0) {
            return this;
        }
        return value.compareTo(this.value) < 0 ? add2Left(value) : add2Right(value);
    }

    /**
     * 往左子树添加
     *
     * @param value
     * @return
     */
    protected TreeNode<T> add2Left(T value) {
        if (this.left == null) {
            this.left = new TreeNode<>(value);
            return this.left;
        }
        return this.left.add(value);
    }

    /**
     * 往右子树添加
     *
     * @param value
     * @return
     */
    protected TreeNode<T> add2Right(T value) {
        if (this.right == null) {
            this.right = new TreeNode<>(value);
            return this.right;
        }
        return this.right.add(value);
    }

    /**
     * 递归镜像
     *
     * @param
     */
    public void recursiveMirror() {
        if (this.left != null) {
            this.left.recursiveMirror();
        }

        if (this.right != null) {
            this.right.recursiveMirror();
        }

        TreeNode<T> leftNode = this.left;
        //左右反转
        this.setLeft(this.right);
        this.setRight(leftNode);
    }

    /**
     * 前序遍历
     * @return
     */
    public List<TreeNode<T>> preVisit() {
        List<TreeNode<T>> list = new ArrayList<>();
        preVisitTree(list);
        return list;
    }

    /**
     * 中序遍历
     * @return
     */
    public List<TreeNode<T>> midVisit() {
        List<TreeNode<T>> list = new ArrayList<>();
        midVisitTree(list);
        return list;
    }

    /**
     * 后序遍历
     * @return
     */
    public List<TreeNode<T>> postVisit() {
        List<TreeNode<T>> list = new ArrayList<>();
        postVisitTree(list);
        return list;
    }

    /**
     * 前序递归
     *
     * @param list
     * @return
     */
    protected void preVisitTree(List<TreeNode<T>> list) {
        list.add(this);
        if (this.left != null) {
            this.left.preVisitTree(list);
        }
        if (this.right != null) {
            this.right.preVisitTree(list);
        }
    }

    /**
     * 中序递归
     *
     * @param list
     * @return
     */
    protected void midVisitTree(List<TreeNode<T>> list) {
        if (this.left != null) {
            this.left.midVisitTree(list);
        }
        list.add(this);
        if (this.right != null) {
            this.right.midVisitTree(list);
        }
    }

    /**
     * 后序递归
     *
     * @param list
     * @return
     */
    protected void postVisitTree(List<TreeNode<T>> list) {
        if (this.left != null) {
            this.left.postVisitTree(list);
        }
        if (this.right != null) {
            this.right.postVisitTree(list);
        }
        list.add(this);
    }
}
