package com.beneu.beneuprod.core.service.impl;

/**
 * <Description>: 插入排序
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/8 21:48
 */
public class InsertSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T> {

    @Override
    public void sort(T[] data, int begin, int end) {
        // 从begin+1 开始往前插入，到最后一个元素结束
        for (int i = begin + 1; i <= end; i++) {
            insert(data, begin, i);
        }
    }

    /**
     * 将data[insert]插入到[begin, insert)中，其中[begin, i)有序
     *
     * @param data
     * @param begin
     * @param insert
     */
    protected void insert(T[] data, int begin, int insert) {
        int index = findFirstIndex(data, begin, insert - 1, data[insert]);
        T value = data[insert];
        // 将[index, insert)的元素往后挪动
        for (int j = insert; j > index; j--) {
            data[j] = data[j-1];
        }
        //插入value
        data[index] = value;
    }

    /**
     * 从data的[begin, end]范围内找到value的插入点(从左到有第一个大于value的位置 或者 插入到end+1)
     * @param data
     * @param begin
     * @param end
     * @param value
     * @return
     */
    protected int findFirstIndex(T[] data, int begin, int end, T value) {
        for (int i = begin; i <= end; i++) {
            if (data[i].compareTo(value) > 0) {
                return i;
            }
        }
        return end + 1;
    }

}
