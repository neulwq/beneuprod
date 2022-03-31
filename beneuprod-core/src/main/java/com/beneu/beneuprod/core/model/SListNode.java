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
public class SListNode<T extends Comparable<T>> implements Comparable<SListNode<T>> {

    /** 节点的值 **/
    protected T value;

    /** next节点 */
    protected transient SListNode<T> next;

    /** 构造函数 */
    public SListNode(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(SListNode<T> listNode) {
        return value.compareTo(listNode.value);
    }
}
