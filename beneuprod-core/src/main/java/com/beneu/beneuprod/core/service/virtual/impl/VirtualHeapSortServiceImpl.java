package com.beneu.beneuprod.core.service.virtual.impl;

import com.beneu.beneuprod.core.service.impl.BaseSortService;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/5 22:35
 */
public class VirtualHeapSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T> {

    @Override
    public void sort(T[] data, int begin, int end) {
        VirtualBeneuPriorityQueue<T> priorityQueue = new VirtualBeneuPriorityQueue<>(data.length + 1);
        for (int i = begin; i <= end; ++i) {
            priorityQueue.add(data[i]);
        }
        for (int i = begin; i <= end; ++i) {
            data[i] = priorityQueue.poll();
        }
    }

    class VirtualBeneuPriorityQueue<T extends Comparable<T>> {
        /** 用于存放数据的数组 */
        protected Object[] array;

        /** 优先队列元素的个数，< array.length */
        protected int size;

        /** 构造函数 */
        public VirtualBeneuPriorityQueue(int size) {
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
            if (size == 0) {
                array[0] = t;
                ++size;
                return true;
            }
            if (size >= array.length) {
                throw new UnsupportedOperationException("队列已满");
            }
            int index = size;
            ++size;

            Comparable<T> addVal = (Comparable<T>)t;
            while (index > 0) {
                int parent = (index - 1) >> 1;
                if (addVal.compareTo((T)array[parent]) > 0) {
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
            //队列为空
            if (size == 0) {
                return null;
            }
            T result = (T) array[0];
            //清空尾节点
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

}
