package com.beneu.beneuprod.core.service;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 21:30
 */
public interface SortService<T extends Comparable<T>> {

    /**
     * 排序整个数组
     *
     * @param data
     * @return
     */
    public void sort(T[] data);

    /**
     * 排序数组指定位置范围[begin,end]
     *
     * @param data
     * @param begin
     * @param end
     */
    public void sort(T[] data, int begin, int end);
}
