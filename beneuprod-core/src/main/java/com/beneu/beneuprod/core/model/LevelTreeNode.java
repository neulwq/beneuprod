package com.beneu.beneuprod.core.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/1/9 22:13
 */
@Setter
@Getter
public class LevelTreeNode<T extends Comparable<T>> extends TreeNode<T> {

    /** 树层级 **/
    private int level;

    /** 位置偏移 */
    private int index;

    /** 节点间隔 */
    private transient final static int gap = 4;

    /** 节点位数 */
    private transient final static int nodeSize = 2;

    /** 构造函数 */
    public LevelTreeNode(T value, int level) {
        this.value = value;
        this.level = level;
    }

    /**
     * 可视化
     *
     * @return
     */
    public String view() {
        List<List<TreeNode<T>>> wideList = this.breadthLevelVisitWithFill();

        List<TreeNode<T>> bottomNodes = wideList.get(wideList.size() - 1);
        int currentIndex = 0;

        for (TreeNode<T> node : bottomNodes) {
            LevelTreeNode levelTreeNode = (LevelTreeNode)node;
            levelTreeNode.index = currentIndex;
            currentIndex += (nodeSize + gap);
        }

        for (int i = wideList.size() - 2; i >= 0; i--) {
            //从倒数第二层开始
            for (TreeNode<T> node : wideList.get(i)) {
                LevelTreeNode levelNode = (LevelTreeNode)node;
                LevelTreeNode leftNode = (LevelTreeNode)levelNode.left;
                LevelTreeNode rightNode = (LevelTreeNode)levelNode.right;
                levelNode.index = (leftNode.index + rightNode.index) / 2;
            }
        }

        for (List<TreeNode<T>> levelNodes : wideList) {
            System.out.println(JSON.toJSONString(levelNodes));
        }

        StringBuilder builder = new StringBuilder();
        for (List<TreeNode<T>> levelNodes : wideList) {
            int levelIndex = 0;
            int rowIndex = 0;
            StringBuilder valueBuilder = new StringBuilder();
            StringBuilder colBuilder = new StringBuilder();
            StringBuilder rowBuilder = new StringBuilder();

            for (TreeNode<T> node : levelNodes) {
                LevelTreeNode<T> levelNode = (LevelTreeNode<T>)node;
                valueBuilder.append(emptyString(levelNode.index - levelIndex)).append(format(levelNode.value));
                colBuilder.append(emptyString(levelNode.index - levelIndex)).append(StringUtils.rightPad("|", nodeSize, " "));
                rowBuilder.append(emptyString(levelNode.index - levelIndex));

                if (levelNode.left != null) {
                    LevelTreeNode leftNode = (LevelTreeNode)levelNode.left;
                    rowBuilder.setLength(leftNode.index + 1);
                    rowBuilder.append(StringUtils.rightPad("-", levelNode.index - leftNode.index - 1, "-"));
                }
                if (levelNode.left != null || levelNode.right != null) {
                    rowBuilder.append(StringUtils.rightPad("-", nodeSize, "-"));
                } else {
                    rowBuilder.append(StringUtils.rightPad("", nodeSize, " "));
                }
                if (levelNode.right != null) {
                    LevelTreeNode rightNode = (LevelTreeNode)levelNode.right;
                    rowBuilder.append(StringUtils.rightPad("-", rightNode.index - levelNode.index - nodeSize, "-"));
                    rowIndex = rightNode.index;
                } else {
                    rowIndex = levelNode.index + nodeSize;
                }

                levelIndex = levelNode.index + nodeSize;

            }
            //打印竖线
            builder.append(colBuilder.toString()).append("\n");
            //打印节点值
            builder.append(valueBuilder.toString()).append("\n");
            //打印横线
            builder.append(rowBuilder.toString()).append("\n");

        }

        return builder.toString();
    }

    protected String emptyString(int size) {
        return StringUtils.leftPad("", size, " ");
    }

    @Override
    public int maxDepth() {
        return super.maxDepth();
    }

    protected String format(T value) {
        return StringUtils.leftPad(value.toString(), nodeSize, "0");
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
     * 往左子树添加
     *
     * @param value
     * @return
     */
    protected TreeNode<T> add2Left(T value) {
        if (this.left == null) {
            this.left = new LevelTreeNode<>(value, level + 1);
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
            this.right = new LevelTreeNode<>(value, level + 1);
            return this.right;
        }
        return this.right.add(value);
    }

    /**
     * 浅拷贝
     *
     * @return
     */
    protected TreeNode<T> shallowClone() {
        return new LevelTreeNode<>(value, level);
    }

}
