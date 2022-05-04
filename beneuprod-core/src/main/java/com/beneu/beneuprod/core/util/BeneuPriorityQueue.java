package com.beneu.beneuprod.core.util;

public class BeneuPriorityQueue<T extends Comparable<T>> {

    /** 用于存放数据的数组 */
    protected Object[] array;

    /** 优先队列元素的个数，< array.length */
    protected int size;

    /** 构造函数 */
    public BeneuPriorityQueue(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("队列长度需要大于0");
        }
        this.array = new Object[size];
    }

    /** 尾部添加 */
    public boolean add(T t) {
        return offer(t);
    }

    /** 尾部添加 */
    public boolean offer(T t) {
        if (size >= array.length) {
            throw new UnsupportedOperationException("队列已满");
        }
        if (size == 0) {
            array[0] = t;
            ++size;
            return true;
        }
        int index = size;
        ++size;

        while (index > 0) {
            Comparable<T> addVal = (Comparable<T>) t;
            int parent = (index - 1) >> 1;
            if (addVal.compareTo((T)array[parent]) >= 0) {
                break;
            }
            array[index] = array[parent];
            index = parent;
        }
        array[index] = t;

        return true;
    }

    /** 返回队列头部元素，并重新构造队列 */
    public T poll() {
        if (size == 0) {
            return null;
        }
        T result = (T) array[0];
        T tail = (T) array[size - 1];
        array[size - 1] = null;
        --size;

        if (size > 0) {
            Comparable<T> key = (Comparable<T>) tail;
            int half = size >>> 1;
            int index = 0;
            while (index < half) {
                int child = (index << 1) + 1; // assume left child is least
                Object c = array[child];
                int right = child + 1;
                if (right < size &&  ((Comparable<T>) c).compareTo((T) array[right]) > 0)
                    c = array[child = right];
                if (key.compareTo((T) c) <= 0)
                    break;
                array[index] = c;
                index = child;
            }
            array[index] = key;
        }
        return result;
    }
}
