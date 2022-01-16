package com.beneu.beneuprod.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/1/15 22:12
 */
@Setter
@Getter
public class AvlTreeNode<T extends Comparable<T>> {

    /** 树层级,可视化时使用 **/
    protected int level;

    /** 树高，叶子节点树高为0 */
    protected transient int height;

    /** 位置偏移，可视化时使用 */
    protected transient int index;

    /** 节点的值 **/
    protected T value;

    /** 左子树 */
    protected transient AvlTreeNode<T> left;

    /** 右子树 */
    protected transient AvlTreeNode<T> right;

    /** 构造函数 */
    public AvlTreeNode(T value, int height) {
        this.value = value;
        this.height = height;
    }
}
