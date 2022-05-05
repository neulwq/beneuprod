package com.beneu.beneuprod.core.service.impl;

import com.beneu.common.util.log.LoggerFormatUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <Description>: 冒泡排序
 *
 * @author beneu
 * @version 1.0
 * @createDate 2020/8/9 15:29
 */
@Slf4j
public class BubbleSortServiceImpl<T extends Comparable<T>> extends BaseSortService<T>  {

    /** [begin,end] */
    @Override
    public void sort(T[] data, int begin, int end) {
        LoggerFormatUtil.info(log, "BubbleSortServiceImpl.sort[{0}, {1}]", begin, end);
        //第一趟进行N-1次比较
        for (int i = begin; i < end; i++) {
            boolean swapFlag = false;
            for (int j = begin; j < end - (i - begin); ++j) {
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
