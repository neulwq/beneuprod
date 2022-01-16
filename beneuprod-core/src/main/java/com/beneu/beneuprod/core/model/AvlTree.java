package com.beneu.beneuprod.core.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/1/15 22:10
 */
@Slf4j
public class AvlTree<T extends Comparable<T>> {

    /** 节点间隔 */
    private transient final static int gap = 2;

    /** 节点位数 */
    private transient final static int nodeSize = 2;

    /** 横标 */
    private transient final static String ROW_TAG = "_";

    /** 空格标 */
    private transient final static String SPACE_TAG = " ";

    /** 空 */
    private transient final static String EMPTY_TAG = "";

    /** 根节点 */
    private AvlTreeNode<T> root;

    /** 构造函数 */
    public AvlTree() {}

    /**
     * 树形可视化
     *
     * @return
     */
    public String treeView() {
        List<List<AvlTreeNode<T>>> wideList = breadthLevelVisitWithFill();

        List<AvlTreeNode<T>> bottomNodes = wideList.get(wideList.size() - 1);
        int currentIndex = 0;

        for (AvlTreeNode<T> node : bottomNodes) {
            node.index = currentIndex;
            currentIndex += (nodeSize + gap);
        }

        for (int i = wideList.size() - 2; i >= 0; i--) {
            //从倒数第二层开始
            for (AvlTreeNode<T> node : wideList.get(i)) {
                node.index = (node.left.index + node.right.index) / 2;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (List<AvlTreeNode<T>> levelNodes : wideList) {
            int levelIndex = 0;
            int rowIndex = 0;
            StringBuilder valueBuilder = new StringBuilder();
            StringBuilder colBuilder = new StringBuilder();
            StringBuilder rowBuilder = new StringBuilder();

            for (AvlTreeNode<T> node : levelNodes) {
                boolean emptyNode = (node.value == null);
                valueBuilder.append(emptyString(node.index - levelIndex)).append(emptyNode ? emptyString(nodeSize) : format(node.value));
                colBuilder.append(emptyString(node.index - levelIndex)).append(emptyNode ? emptyString(nodeSize) : StringUtils.rightPad("|", nodeSize, SPACE_TAG));

                levelIndex = valueBuilder.length();

                rowBuilder.append(emptyString(node.index - rowIndex));
                if(emptyNode) {
                    rowBuilder.append(emptyString(nodeSize));
                    rowIndex = rowBuilder.length();
                    continue;
                }

                if (isNotEmpty(node.left)) {
                    rowBuilder.setLength(node.left.index + 1);
                    rowBuilder.append(StringUtils.rightPad(EMPTY_TAG, node.index - node.left.index - 1, ROW_TAG));
                }
                if (isNotEmpty(node.left) || isNotEmpty(node.right)) {
                    rowBuilder.append(StringUtils.rightPad(EMPTY_TAG, nodeSize, ROW_TAG));
                } else {
                    rowBuilder.append(emptyString(nodeSize));
                }
                if (isNotEmpty(node.right)) {
                    rowBuilder.append(StringUtils.rightPad(EMPTY_TAG, node.right.index - node.index - nodeSize, ROW_TAG));
                }

                rowIndex = rowBuilder.length();

            }
            /** 调试信息
             System.out.println(colBuilder.toString());
             System.out.println(valueBuilder.toString());
             System.out.println(rowBuilder.toString());
             */
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
    protected boolean isNotEmpty(AvlTreeNode<T> node) {
        return node != null && node.value != null;
    }

    /**
     * 按层级遍历
     *
     * @return
     */
    public List<List<AvlTreeNode<T>>> breadthLevelVisitWithFill() {
        AvlTreeNode<T> tree = deepClone(root);
        tree.level = 0;
        List<List<AvlTreeNode<T>>> wideList = new ArrayList<>();
        for (int i = 0; i <= height(tree); i++) {
            wideList.add(new ArrayList<>());
        }

        int maxLevel = height(tree);
        Queue<AvlTreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(tree);

        for (AvlTreeNode<T> node = queue.poll(); node != null; node = queue.poll()) {
            wideList.get(node.level).add(node);
            if (node.left == null && node.level < maxLevel) {
                node.left = new AvlTreeNode<>(null, node.height - 1);
            }
            if (node.left != null) {
                node.left.level = node.level + 1;
                queue.offer(node.left);
            }
            if (node.right == null && node.level < maxLevel) {
                node.right = new AvlTreeNode<>(null, node.height - 1);
            }
            if (node.right != null) {
                node.right.level = node.level + 1;
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
    public List<List<AvlTreeNode<T>>> breadthLevelVisit() {
        AvlTreeNode<T> tree = deepClone(root);
        tree.level = 0;
        List<List<AvlTreeNode<T>>> wideList = new ArrayList<>();
        for (int i = 0; i <= height(); i++) {
            wideList.add(new ArrayList<>());
        }

        Queue<AvlTreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(tree);

        for (AvlTreeNode<T> node = queue.poll(); node != null; node = queue.poll()) {
            wideList.get(node.level).add(node);
            if (node.left != null) {
                node.left.level = node.level + 1;
                queue.offer(node.left);
            }
            if (node.right != null) {
                node.right.level = node.level + 1;
                queue.offer(node.right);
            }
        }
        return wideList;
    }


    /**
     * 深拷贝
     *
     * @return
     */
    protected AvlTreeNode<T> deepClone(AvlTreeNode<T> node) {
        AvlTreeNode<T> treeNode = shallowClone(node);
        if (node.left != null) {
            treeNode.left = deepClone(node.left);
        }
        if (node.right != null) {
            treeNode.right = deepClone(node.right);
        }
        return treeNode;
    }

    /**
     * 浅拷贝
     *
     * @return
     */
    protected AvlTreeNode<T> shallowClone(AvlTreeNode<T> node) {
        AvlTreeNode<T> clonNode = new AvlTreeNode<>(node.value, node.height);
        clonNode.level = node.level;
        return clonNode;
    }

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
    protected int height(AvlTreeNode<T> node) {
        if (node == null) {
            //叶子节点树高为0
            return -1;
        }
        return node.height;
    }

    /**
     * 广度优先遍历
     *
     * @return
     */
    public List<AvlTreeNode<T>> breadthVisit() {
        List<AvlTreeNode<T>> values = new ArrayList<>();
        Queue<AvlTreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        for (AvlTreeNode<T> visitNode = queue.poll(); visitNode != null; visitNode = queue.poll()) {
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
        Queue<AvlTreeNode<T>> queue = new ArrayDeque<>();
        queue.offer(root);

        for (AvlTreeNode<T> visitNode = queue.poll(); visitNode != null; visitNode = queue.poll()) {
            if (visitNode.left != null) {
                queue.offer(visitNode.left);
            }
            if (visitNode.right != null) {
                queue.offer(visitNode.right);
            }
            AvlTreeNode<T> leftNode = visitNode.left;
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
     * 添加
     *
     * @param value
     */
    public void add(T value) {
        root = add(root, value);
        log.info("add:" + value + ";\n" + this.treeView());
    }


    /**
     * 添加元素
     *
     * @param value
     * @return
     */
    protected AvlTreeNode<T> add(AvlTreeNode<T> node, T value) {
        if (node == null) {
            return new AvlTreeNode<>(value, 0);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = add(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = add(node.right, value);
        }
        node = balance(node);
        return node;
    }

    /**
     * 递归镜像
     *
     * @param
     */
    protected void recursiveMirror(AvlTreeNode<T> node) {
        if (node.left != null) {
            recursiveMirror(node.left);
        }

        if (node.right != null) {
            recursiveMirror(node.right);
        }

        AvlTreeNode<T> leftNode = node.left;
        //左右反转
        node.setLeft(node.right);
        node.setRight(leftNode);
    }

    /**
     * 前序遍历
     * @return
     */
    public List<AvlTreeNode<T>> preVisit() {
        List<AvlTreeNode<T>> list = new ArrayList<>();
        preVisitTree(list, root);
        return list;
    }

    /**
     * 中序遍历
     * @return
     */
    public List<AvlTreeNode<T>> midVisit() {
        List<AvlTreeNode<T>> list = new ArrayList<>();
        midVisitTree(list, root);
        return list;
    }

    /**
     * 后序遍历
     * @return
     */
    public List<AvlTreeNode<T>> postVisit() {
        List<AvlTreeNode<T>> list = new ArrayList<>();
        postVisitTree(list, root);
        return list;
    }

    /**
     * 前序递归
     *
     * @param list
     * @return
     */
    protected void preVisitTree(List<AvlTreeNode<T>> list, AvlTreeNode<T> node) {
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
    protected void midVisitTree(List<AvlTreeNode<T>> list, AvlTreeNode<T> node) {
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
    protected void postVisitTree(List<AvlTreeNode<T>> list, AvlTreeNode<T> node) {
        if (node.left != null) {
            postVisitTree(list, node.left);
        }
        if (node.right != null) {
            postVisitTree(list, node.right);
        }
        list.add(node);
    }

    protected String emptyString(int size) {
        return StringUtils.leftPad(EMPTY_TAG, size, SPACE_TAG);
    }

    protected String format(T value) {
        return StringUtils.leftPad(value.toString(), nodeSize, "0");
    }

    /**
     * 平衡操作
     */
    protected AvlTreeNode<T> balance(AvlTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        if (height(node.left) - height(node.right) > 1) {
            if(height(node.left.left) >= height(node.left.right)) {
                node = LL(node);
            } else {
                node = LR(node);
            }
        }else if(height(node.right) - height(node.left) > 1) {
            if(height(node.right.right) >= height(node.right.left)) {
                node = RR(node);
            } else {
                node = RL(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    //LL情况
    protected AvlTreeNode<T> LL(AvlTreeNode<T> node) {
        AvlTreeNode<T> temp = node.left;
        node.left = temp.right;
        temp.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
        return temp;
    }

    //RR情况
    protected AvlTreeNode<T> RR(AvlTreeNode<T> node) {
        AvlTreeNode<T> temp = node.right;
        node.right = temp.left;
        temp.left = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
        return temp;
    }

    //LR情况
    protected AvlTreeNode<T> LR(AvlTreeNode<T> node) {
        node.left = RR(node.left);
        return LL(node);
    }

    //RL情况
    protected AvlTreeNode<T> RL(AvlTreeNode<T> node) {
        node.right = LL(node.right);
        return RR(node);
    }
}
