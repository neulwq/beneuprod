package com.beneu.beneuprod.core.service.impl;

import com.beneu.beneuprod.core.util.BeneuPriorityQueue;

import java.util.PriorityQueue;

/**
 * <Description>: 堆排序
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/2 21:48
 */
public class HeapSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T>  {

    @Override
    public void sort(T[] data, int begin, int end) {
        BeneuPriorityQueue<T> priorityQueue = new BeneuPriorityQueue<>(data.length + 1);
        for (int i = begin; i <= end; ++i) {
            priorityQueue.add(data[i]);
        }
        for (int i = begin; i <= end; ++i) {
            data[i] = priorityQueue.poll();
        }
    }
}
