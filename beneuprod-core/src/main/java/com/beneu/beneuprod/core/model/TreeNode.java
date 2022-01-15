package com.beneu.beneuprod.core.model;

import com.alibaba.fastjson.JSON;
import com.beneu.common.util.log.MessageFormatUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
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

    /** 节点间隔 */
    private transient final static int gap = 4;

    /** 节点位数 */
    private transient final static int nodeSize = 2;

    /** 横标 */
    private transient final static String ROW_TAG = "_";

    /** 空标 */
    private transient final static String ENPTY_TAG = " ";

    /** 树层级 **/
    private int level;

    /** 位置偏移 */
    private transient int index;

    /** 节点的值 **/
    protected T value;

    /** 左子树 */
    protected transient TreeNode<T> left;

    /** 右子树 */
    protected transient TreeNode<T> right;

    /** 构造函数 */
    public TreeNode(T value, int level) {
        this.value = value;
        this.level = level;
    }

    /**
     * 树形可视化
     *
     * @return
     */
    public String treeView() {
        List<List<TreeNode<T>>> wideList = this.breadthLevelVisitWithFill();

        List<TreeNode<T>> bottomNodes = wideList.get(wideList.size() - 1);
        int currentIndex = 0;

        for (TreeNode<T> node : bottomNodes) {
            node.index = currentIndex;
            currentIndex += (nodeSize + gap);
        }

        for (int i = wideList.size() - 2; i >= 0; i--) {
            //从倒数第二层开始
            for (TreeNode<T> node : wideList.get(i)) {
                node.index = (node.left.index + node.right.index) / 2;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (List<TreeNode<T>> levelNodes : wideList) {
            int levelIndex = 0;
            int rowIndex = 0;
            StringBuilder valueBuilder = new StringBuilder();
            StringBuilder colBuilder = new StringBuilder();
            StringBuilder rowBuilder = new StringBuilder();

            for (TreeNode<T> node : levelNodes) {
                boolean emptyNode = (node.value == null);
                valueBuilder.append(emptyString(node.index - levelIndex)).append(emptyNode ? emptyString(nodeSize) : format(node.value));
                colBuilder.append(emptyString(node.index - levelIndex)).append(emptyNode ? emptyString(nodeSize) : StringUtils.rightPad("|", nodeSize, ENPTY_TAG));
                rowBuilder.append(emptyString(node.index - rowIndex));

                if (isNotEmpty(node.left)) {
                    rowBuilder.setLength(node.left.index + 1);
                    rowBuilder.append(StringUtils.rightPad(ROW_TAG, node.index - node.left.index - 1, ROW_TAG));
                }
                if (isNotEmpty(node.left) || isNotEmpty(node.right)) {
                    rowBuilder.append(StringUtils.rightPad(ROW_TAG, nodeSize, ROW_TAG));
                } else {
                    rowBuilder.append(emptyString(nodeSize));
                }
                if (isNotEmpty(node.right)) {
                    rowBuilder.append(StringUtils.rightPad(ROW_TAG, node.right.index - node.index - nodeSize, ROW_TAG));
                }

                rowIndex = rowBuilder.length();
                levelIndex = valueBuilder.length();

            }
            System.out.println(colBuilder.toString());
            System.out.println(valueBuilder.toString());
            System.out.println(rowBuilder.toString());
            //打印竖线
            builder.append(colBuilder.toString()).append("\n");
            //打印节点值
            builder.append(valueBuilder.toString()).append("\n");
            //打印横线
            builder.append(rowBuilder.toString()).append("\n");

        }

        return builder.toString();
    }

    /**
     * 子树非空
     *
     * @param node
     * @return
     */
    protected boolean isNotEmpty(TreeNode<T> node) {
        return node != null && node.value != null;
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
        return level;
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
        return new TreeNode<>(value, level);
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
            this.left = new TreeNode<>(value, level + 1);
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
            this.right = new TreeNode<>(value, level + 1);
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

    protected String emptyString(int size) {
        return StringUtils.leftPad("", size, ENPTY_TAG);
    }

    protected String format(T value) {
        return StringUtils.leftPad(value.toString(), nodeSize, "0");
    }
}
