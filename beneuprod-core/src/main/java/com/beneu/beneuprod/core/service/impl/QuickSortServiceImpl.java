package com.beneu.beneuprod.core.service.impl;

import org.springframework.stereotype.Service;

/**
 * <Description>: 递归快排
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 21:34
 */
@Service
public class QuickSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T> {

    /**
     * 快速排序（递归实现）
     *
     * @param data
     * @param begin
     * @param end
     */
    @Override
    public void sort(T[] data, int begin, int end) {
        if (begin < end) {
            int baseIndex = findBaseIndex(data, begin, end);
            swap(data, begin, baseIndex);
            //递归左侧
            sort(data, begin, baseIndex - 1);
            sort(data, baseIndex + 1, end);
        }
    }

    /**
     * 找到base元素的索引
     * @param data
     * @param begin
     * @param end
     * @return
     */
    protected int findBaseIndex(T[] data, int begin, int end) {
        T base = data[begin];
        int left = begin;
        int right = end + 1;
        while (true) {
            //从left往右遍历，直到找到第一个大于base的位置
            while (left < end && data[++left].compareTo(base) <= 0);
            //从right往左遍历，直到找到第一个小于base的位置
            while (right > begin && data[--right].compareTo(base) >= 0);
            if (left < right) {
                swap(data, left, right);
            } else {
                break;
            }
        }
        return right;
    }
}
