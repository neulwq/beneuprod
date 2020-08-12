package com.beneu.beneuprod.core.service.impl;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/12 22:30
 */
public class UnRecursiveMergeSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T> {

    @Override
    public void sort(T[] data, int begin, int end) {
        if (begin < end) {
            int length = (end - begin + 1);
            int step = 1;
            while (step < length) {
                for (int j = begin; j + step <= end + 1; j += step * 2) {
                    merge(data, j, j + step - 1, Math.min(j + 2 * step - 1, end));
                }
                step *= 2;
            }
        }
    }
}
