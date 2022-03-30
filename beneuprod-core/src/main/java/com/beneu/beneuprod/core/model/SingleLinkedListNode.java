package com.beneu.beneuprod.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/3/30 23:24
 */
@Setter
@Getter
public class SingleLinkedListNode<T extends Comparable<T>> {

    /** 节点的值 **/
    protected T value;

    /** next节点 */
    protected transient SingleLinkedListNode<T> next;

    /** 构造函数 */
    public SingleLinkedListNode(T value) {
        this.value = value;
    }
}
