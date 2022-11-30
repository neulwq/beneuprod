package com.beneu.beneuprod.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class LinkedListTest {

    class Node<T extends Comparable<T>> {
        protected T value;
        protected Node<T> next;
        public Node(T value) {this.value = value;}

        public Node<T> setNext(Node<T> next) {
            this.next = next;
            return next;
        }

        public Node(T[] array) {
            int size = array.length;
            this.value = array[0];
            Node tmp = this;
            for (int i = 1; i < size; ++i) {
                tmp = tmp.setNext(new Node(array[i]));
            }
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(value).append(",");
            Node<T> tmp = next;
            while (tmp != null) {
                builder.append(tmp.value).append(",");
                tmp = tmp.next;
            }
            builder.setLength(builder.length() - 1);
            return builder.toString();
        }
    }

    @Test
    public void testMergeNode() {
        Assert.assertTrue(mergeNode(null, null) == null);
        Node<Integer> node = mergeNode(new Node<Integer>(1), null);
        Assert.assertTrue(node.toString().equals("1"));

        node = mergeNode(null, new Node<Integer>(1));
        Assert.assertTrue(node.toString().equals("1"));

        node = mergeNode(new Node<Integer>(1), new Node<Integer>(1));
        Assert.assertTrue(node.toString().equals("1,1"));

        node = mergeNode(new Node<Integer>(new Integer[] {1,3,5}), new Node<Integer>(new Integer[]{2,4,6}));
        Assert.assertTrue(node.toString().equals("1,2,3,4,5,6"));

        node = mergeNode(new Node<Integer>(new Integer[] {1,3,5}), new Node<Integer>(new Integer[]{3,4,6}));
        Assert.assertTrue(node.toString().equals("1,3,3,4,5,6"));
    }

    /**
     * 测试单链表回文字符串
     */
    @Test
    public void testHuiWenString() {
        Assert.assertTrue(!isHuiWen(null));

        Assert.assertTrue(isHuiWen(new Node(new Character[] {'a'})));

        Assert.assertTrue(isHuiWen(new Node(new Character[] {'a', 'a'})));

        Assert.assertTrue(!isHuiWen(new Node(new Character[] {'a', 'b'})));

        Assert.assertTrue(isHuiWen(new Node(new Character[] {'a', 'b', 'a'})));
    }

    @Test
    public void testMidNode() {
        Node mid = midNode(null);
        Assert.assertTrue(mid == null);

        mid = midNode(new Node(new Character[] {'a'}));
        Assert.assertTrue(mid.toString().equals("a"));

        mid = midNode(new Node(new Character[] {'a', 'b'}));
        Assert.assertTrue(mid.toString().equals("b"));

        mid = midNode(new Node(new Character[] {'a', 'b', 'a'}));
        Assert.assertTrue(mid.toString().equals("b,a"));
    }

    @Test
    public void testRevert() {
        Node revert = revertNode(null);
        Assert.assertTrue(revert == null);

        revert = revertNode(new Node(new Character[] {'a'}));
        Assert.assertTrue(revert.toString().equals("a"));

        revert = revertNode(new Node(new Character[] {'a', 'b'}));
        Assert.assertTrue(revert.toString().equals("b,a"));

        revert = revertNode(new Node(new Character[] {'a', 'b', 'a'}));
        Assert.assertTrue(revert.toString().equals("a,b,a"));
    }

    @Test
    public void testHasCycle() {
        Assert.assertTrue(!hasCycle(null));

        Node<Character> node = new Node<Character>('a');
        Assert.assertTrue(!hasCycle(node));

        Node<Character> tail = new Node<Character>('b');
        node.next = tail;
        tail.next = node;
        Assert.assertTrue(hasCycle(node));

        Node<Character> mid = new Node<Character>('b');
        node.next = mid;
        mid.next = tail;
        tail.next = null;
        Assert.assertTrue(!hasCycle(node));

        tail.next = mid;
        Assert.assertTrue(hasCycle(node));

        tail.next = node;
        Assert.assertTrue(hasCycle(node));

        Node<Character> last = new Node<Character>('b');
        tail.next = last;
        Assert.assertTrue(!hasCycle(node));
    }

    protected <T extends Comparable<T>> boolean isHuiWen(Node<T> list) {
        if (list == null) {
            return false;
        }
        Node<T> mid = midNode(list);
        Node<T> tail = revertNode(mid);

        Node<T> begin = list;
        while (begin != null && tail != null) {
            if (!begin.value.equals(tail.value)) {
                return false;
            }
            begin = begin.next;
            tail = tail.next;
        }
        return true;
    }

    protected <T extends Comparable<T>> Node<T> midNode(Node<T> list) {
        if (list == null) {
            return list;
        }
        Node<T> left = list, right = list;
        while (right != null && right.next != null) {
            right = right.next.next;
            left = left.next;
        }
        return left;
    }

    /**
     * 反转链表
     *
     * @param node
     */
    protected <T extends Comparable<T>> Node<T> revertNode(Node<T> node) {
        if (node == null) {
            return node;
        }
        Node<T> head = node;
        Node<T> next = node.next;
        node.next = null;
        while (next != null) {
            head = next;
            next = next.next;
            head.next = node;
            node = head;
        }
        return head;
    }

    protected <T extends Comparable<T>> boolean hasCycle(Node<T> node) {
        if (node == null) {
            return false;
        }
        Node<T> slow = node;
        Node<T> quick = node.next;

        while (true) {
            if (quick == null || quick.next == null) {
                return false;
            } else if (slow == quick) {
                return true;
            }
            slow = slow.next;
            quick = quick.next.next;
        }
    }

    protected <T extends Comparable<T>> Node<T> mergeNode(Node<T> left, Node<T> right) {
        if (left == null || right == null) {
            return left == null ? right : left;
        }
        Node<T> head = new Node<T>((T)null);
        Node<T> cur = head;

        while (left != null || right != null) {
            if (left == null || right == null) {
                cur.next = (left == null) ? right : left;
                break;
            }
            if (left.value.compareTo(right.value) <= 0) {
                cur.next = new Node<T>(left.value);
                left = left.next;
            } else {
                cur.next = new Node<T>(right.value);
                right = right.next;
            }
            cur = cur.next;
        }
        return head.next;
    }
}
