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
            int right = doQuickSort(data, begin, end);
            //递归左边子树
            sort(data, begin, right - 1);
            //递归右边子树
            sort(data, right + 1, end);
        }
    }

    /**
     * 单趟快排
     *
     * @param data
     * @param begin
     * @param end
     * @return
     */
    protected int doQuickSort(T[] data, int begin, int end) {
        //以第一个元素作为分界参考值
        T base = data[begin];
        int left = begin;
        int right = end + 1;
        while (true) {
            //从左边开始遍历找到第一个大于分界值的索引 或者到达end位置
            while (left < end && data[++left].compareTo(base) <= 0) ;
            //从右边开始遍历找到第一个小于分界值的索引 或者到达begin位置
            while (right > begin && data[--right].compareTo(base) >= 0) ;
            if (left < right) {
                swap(data, left, right);
            } else {
                break;
            }
        }
        swap(data, begin, right);
        return right;
    }

}
