package com.beneu.beneuprod.core.service.impl;

import com.beneu.beneuprod.core.service.SortService;

import java.lang.reflect.Array;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 21:43
 */
public abstract class BaseSortService<T extends Comparable<T>> implements SortService<T> {

    @Override
    public void sort(T[] data) {
        //调用重载方法
        sort(data, 0, data.length - 1);
    }

    /**
     * 交换元素
     *
     * @param data
     * @param source 起始索引
     * @param target 目标索引
     */
    protected void swap(T[] data, int source, int target) {
        T element = data[source];
        data[source] = data[target];
        data[target] = element;
    }

    /**
     * 合并
     * @param data
     * @param begin
     * @param mid
     * @param end
     */
    protected void merge(T[] data, int begin, int mid, int end) {
        T[] result = (T[]) Array.newInstance(data[begin].getClass(), (end - begin + 1));
        int left = begin;
        int right = mid + 1;
        for (int i = 0; i < result.length; i++) {
            if (left == mid + 1) {
                //left已经拷贝完成，将right数组剩余元素copy过去即可
                for (int j = right; j <= end; j++) {
                    result[i++] = data[j];
                }
                break;
            }
            if (right == end + 1) {
                //right已经拷贝完成，将left数组剩余元素copy过去即可
                for (int j = left; j <= mid; j++) {
                    result[i++] = data[j];
                }
                break;
            }
            if (data[left].compareTo(data[right]) <= 0) {
                result[i] = data[left++];
            } else {
                result[i] = data[right++];
            }
        }
        for (int i = 0; i < result.length; i++) {
            data[begin++] = result[i];
        }
    }

}
