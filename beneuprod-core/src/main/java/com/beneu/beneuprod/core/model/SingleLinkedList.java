package com.beneu.beneuprod.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/3/30 23:23
 */
public class SingleLinkedList<T extends Comparable<T>> {

    /** 头节点 */
    private SingleLinkedListNode<T> head;

    /** 构造函数 */
    public SingleLinkedList() {}

    /**
     * 反转单链表
     */
    public void revert() {
        head = revert(head);
    }

    public void add(T value) {
        head = add(head, value);
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
     * 遍历
     * @return
     */
    public List<SingleLinkedListNode<T>> visit() {
        List<SingleLinkedListNode<T>> list = new ArrayList<>();
        SingleLinkedListNode<T> current = head;
        while (current != null) {
            list.add(current);
            current = current.next;
        }
        return list;
    }

    protected SingleLinkedListNode<T> add(SingleLinkedListNode<T> node, T value) {
        if (node == null) {
            return new SingleLinkedListNode<>(value);
        }
        SingleLinkedListNode<T> current = node;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new SingleLinkedListNode<>(value);
        return node;
    }

    protected SingleLinkedListNode<T> revert(SingleLinkedListNode<T> node) {
        if (node == null) {
           return node;
        }
        SingleLinkedListNode<T> pre = node;
        SingleLinkedListNode<T> current = node.next;
        //头结点next清空
        node.next = null;
        while (current != null) {
            SingleLinkedListNode<T> post = current.next;
            current.next = pre;
            pre = current;
            current = post;

        }
        return pre;
    }


}
