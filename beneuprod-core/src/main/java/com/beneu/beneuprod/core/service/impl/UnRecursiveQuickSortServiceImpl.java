package com.beneu.beneuprod.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <Description>: 非递归快速排序
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 21:39
 */
@Service
public class UnRecursiveQuickSortServiceImpl<T extends Comparable<T>> extends QuickSortServiceImpl<T> {

    @Override
    public void sort(T[] data, int begin, int end) {
        Queue<Object[]> sortQueue = new ArrayDeque<>();
        sortQueue.offer(new Object[]{data, begin, end});
        for (Object[] sortNode = sortQueue.poll(); sortNode != null; sortNode = sortQueue.poll()) {
            T[] sortData = (T[]) sortNode[0];
            int sortBegin = (int) sortNode[1];
            int sortEnd = (int) sortNode[2];

            if (sortBegin < sortEnd) {
                int baseIndex = findBaseIndex(sortData, sortBegin, sortEnd);
                swap(sortData, sortBegin, baseIndex);
                sortQueue.offer(new Object[]{sortData, sortBegin, baseIndex - 1});
                sortQueue.offer(new Object[]{sortData, baseIndex + 1, sortEnd});
            }
        }
    }
}
