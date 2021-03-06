package com.beneu.beneuprod.core.service.impl;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 23:21
 */
public class MergeSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T> {

    @Override
    public void sort(T[] data, int begin, int end) {
        if (begin < end) {
            //找出中间元素
            int mid = (begin + end) / 2;
            sort(data, begin, mid);
            sort(data, mid + 1, end);
            merge(data, begin, mid, end);
        }
    }

}
