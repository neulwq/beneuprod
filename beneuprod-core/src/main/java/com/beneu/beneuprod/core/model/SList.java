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
public class SList<T extends Comparable<T>> {

    /** 头节点 */
    private SListNode<T> head;

    /** 构造函数 */
    public SList() {}

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
    public List<SListNode<T>> visit() {
        List<SListNode<T>> list = new ArrayList<>();
        SListNode<T> current = head;
        while (current != null) {
            list.add(current);
            current = current.next;
        }
        return list;
    }

    /**
     * 合并有序链表
     *
     * @param list
     */
    public void merge(SList<T> list) {
        head = merge(head, list.head);
    }

    /**
     * 删除第index个节点
     *
     * @param index
     */
    public void remove(int index) {
        head = remove(head, index);
    }

    /**
     * 查找中间节点
     *
     * @return
     */
    public SListNode<T> middle() {
        return middle(head);
    }

    public boolean hasCycle() {
        return hasCycle(head);
    }

    protected boolean hasCycle(SListNode<T> node) {
        SListNode<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    protected SListNode<T> middle(SListNode<T> node) {
        int count = 0;
        SListNode<T> tmp = node;
        while (tmp != null) {
            ++count;
            tmp = tmp.next;
        }

        SListNode<T> current = node;
        int index = 0;
        while (index != count/2) {
            ++index;
            current = current.next;
        }
        return current;
    }

    protected SListNode<T> remove(SListNode<T> head, int index) {
        int count = 0;
        SListNode<T> tmp = head;
        while (tmp != null) {
            ++count;
            tmp = tmp.next;
        }
        int size = count - index;
        if (size == 0) {
            return head.next;
        }
        int current = 0;
        tmp = head;
        while (current < size - 1) {
            tmp = tmp.next;
            ++current;
        }
        tmp.next = tmp.next.next;

        return head;
    }



    protected SListNode<T> merge(SListNode<T> one, SListNode<T> two) {
        if (one == null || two == null) {
            return (one == null) ? two : one;
        }
        SListNode<T> head = null;
        if (one.compareTo(two) > 0) {
            head = two;
            two = two.next;
        } else {
            head = one;
            one = one.next;
        }
        SListNode<T> current = head;


        while (one != null && two != null) {
            if (one.compareTo(two) > 0) {
                current.next = two;
                current = two;
                two = two.next;
            } else {
                current.next = one;
                current = one;
                one = one.next;
            }
        }
        while (one != null) {
            current.next = one;
            current = one;
            one = one.next;
        }
        while (two != null) {
            current.next = two;
            current = two;
            two = two.next;
        }

        return head;
    }

    protected SListNode<T> add(SListNode<T> node, T value) {
        if (node == null) {
            return new SListNode<>(value);
        }
        SListNode<T> current = node;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new SListNode<>(value);
        return node;
    }

    protected SListNode<T> revert(SListNode<T> node) {
        if (node == null) {
           return node;
        }
        SListNode<T> pre = node;
        SListNode<T> current = node.next;
        //头结点next清空
        node.next = null;
        while (current != null) {
            SListNode<T> post = current.next;
            current.next = pre;
            pre = current;
            current = post;

        }
        return pre;
    }


}
