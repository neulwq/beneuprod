package com.beneu.beneuprod.core.service.impl;

/**
 * <Description>: 选择排序
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/9 15:11
 */
public class SelectSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T> {

    @Override
    public void sort(T[] data, int begin, int end) {
        //遍历N-1趟
        for (int i = begin; i < end; i++) {
            //记录最小的元素索引
            int minIndex = i;
            for (int j = i + 1; j <= end; j++) {
                if (data[minIndex].compareTo(data[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                //swap
                T value = data[minIndex];
                data[minIndex] = data[i];
                data[i] = value;
            }
        }
    }
}
