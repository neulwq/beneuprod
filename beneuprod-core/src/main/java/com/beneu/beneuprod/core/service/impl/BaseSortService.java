package com.beneu.beneuprod.core.service.impl;

import com.beneu.beneuprod.core.service.SortService;

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

}
