package com.beneu.beneuprod.core.service.impl;

/**
 * <Description>: 冒泡排序
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/9 15:29
 */
public class BubbleSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T>  {

    /** [begin,end] */
    @Override
    public void sort(T[] data, int begin, int end) {
        //第一趟进行N-1次比较
        for (int i = begin; i < end; i++) {
            boolean swapFlag = false;
            for (int j = 0; j < end - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j+1);
                    swapFlag = true;
                }
            }
            if (!swapFlag) {
                break;
            }
        }
    }
}
