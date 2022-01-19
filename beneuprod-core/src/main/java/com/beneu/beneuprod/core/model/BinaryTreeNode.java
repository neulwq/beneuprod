package com.beneu.beneuprod.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/1/19 22:21
 */
@Setter
@Getter
public class BinaryTreeNode<T extends Comparable<T>> {

    /** 节点的值 **/
    protected T value;

    /** 左子树 */
    protected transient BinaryTreeNode<T> left;

    /** 右子树 */
    protected transient BinaryTreeNode<T> right;

    /** 构造函数 */
    public BinaryTreeNode(T value) {
        this.value = value;
    }

}
