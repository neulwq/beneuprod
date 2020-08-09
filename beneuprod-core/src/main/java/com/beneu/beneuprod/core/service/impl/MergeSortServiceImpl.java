package com.beneu.beneuprod.core.service.impl;

import com.beneu.beneuprod.util.ArrayMergeUtil;

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

    /**
     * 合并两个有序数组
     *
     * @param data
     * @param begin
     * @param mid
     * @param end
     */
    protected void merge(T[] data, int begin, int mid, int end) {
        T[] mergeResult = ArrayMergeUtil.mergeArray(data, begin, mid, data, mid + 1, end);
        for (int i = 0; i < mergeResult.length; i++) {
            data[begin + i] = mergeResult[i];
        }
    }


    /**
     * 合并
     * @param data
     * @param begin
     * @param mid
     * @param end
     */
    /**
    protected void merge(T[] data, int begin, int mid, int end) {
        T[] result = (T[]) Array.newInstance(data[begin].getClass(), (end - begin + 1));
        int left = begin;
        int right = mid + 1;
        for (int i = 0; i < result.length; i++) {
            if (left == mid + 1) {
                //left已经拷贝完成，将right数组剩余元素copy过去即可
                ArrayMergeUtil.coopyArray(data, result, right, i, (end - right + 1));
                break;
            }
            if (right == end + 1) {
                //right已经拷贝完成，将left数组剩余元素copy过去即可
                ArrayMergeUtil.coopyArray(data, result, left, i, (mid - left + 1));
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
    **/
}
